package com.ljq.demo.springboot.aws.iot.server.util;

import com.ljq.demo.springboot.aws.iot.common.config.AwsIotAccountConfig;
import com.ljq.demo.springboot.aws.iot.server.vo.CertificateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.profiles.ProfileFile;
import software.amazon.awssdk.profiles.ProfileProperty;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.*;
import software.amazon.awssdk.utils.StringInputStream;

import java.util.Objects;

/**
 * @Description: AWS iot 服务端工具类
 * @Author: junqiang.lu
 * @Date: 2022/7/21
 */
@Slf4j
@Component
public class AwsIotServerClientComponent {

    private AwsIotServerClientComponent(){
    }

    @Autowired
    private AwsIotAccountConfig iotAccountConfig;

    private IotClient iotClient;

    /**
     * 初始化 iot 客户端
     *
     * @return
     */
    @Bean(value = "iotServerClient")
    public IotClient iotClient() {
        StringBuilder cfgBuilder = new StringBuilder("[default]\n");
        cfgBuilder.append(ProfileProperty.AWS_ACCESS_KEY_ID).append(" = ").append(iotAccountConfig.getAccessKeyId())
                .append("\n");
        cfgBuilder.append(ProfileProperty.AWS_SECRET_ACCESS_KEY).append(" = ").append(iotAccountConfig.getSecretAccessKey())
                .append("\n");
        ProfileFile profileFile = ProfileFile.builder()
                .content(new StringInputStream(cfgBuilder.toString()))
                .type(ProfileFile.Type.CONFIGURATION).build();
        AwsCredentialsProviderChain awsCredentialsProviderChain = AwsCredentialsProviderChain.of(
                ProfileCredentialsProvider.builder().profileFile(profileFile).build());
        if (Objects.isNull(iotClient)) {
            synchronized (AwsIotServerClientComponent.class) {
                if (Objects.isNull(iotClient)) {
                    iotClient = IotClient.builder()
                            .credentialsProvider(awsCredentialsProviderChain)
                            .region(Region.CN_NORTHWEST_1)
                            .build();
                }
            }
        }
        return iotClient;
    }

    /**
     * 创建物品类型
     *
     * @param thingType 物品类型
     */
    public boolean createThingType(String thingType) {
        Tag tag = Tag.builder().key("type").value(thingType).build();
        CreateThingTypeRequest request = CreateThingTypeRequest.builder()
                .thingTypeName(thingType)
                .tags(tag).build();
        CreateThingTypeResponse response = iotClient.createThingType(request);
        return response.sdkHttpResponse().isSuccessful();
    }

    /**
     * 创建物品
     *
     * @param thingName 物品名称
     * @param thingType 物品类型
     * @return
     */
    public boolean createThing(String thingName, String thingType) {
        CreateThingRequest request = CreateThingRequest.builder()
                .thingName(thingName)
                .thingTypeName(thingType).build();
        CreateThingResponse response = iotClient.createThing(request);
        return response.sdkHttpResponse().isSuccessful();
    }

    /**
     * 创建证书
     *
     * @return
     */
    public CertificateVo createCert() {
        CreateKeysAndCertificateRequest request = CreateKeysAndCertificateRequest.builder().setAsActive(true).build();
        CreateKeysAndCertificateResponse response = iotClient.createKeysAndCertificate(request);
        if (response.sdkHttpResponse().isSuccessful()) {
            CertificateVo certVo = new CertificateVo();
            certVo.setCertificateArn(response.certificateArn());
            certVo.setCertificateId(response.certificateId());
            certVo.setCertificatePem(response.certificatePem());
            certVo.setPublicKey(response.keyPair().publicKey());
            certVo.setPrivateKey(response.keyPair().privateKey());
            return certVo;
        }
        return null;
    }

    /**
     * 绑定物品与证书
     *
     * @param certArn 证书资源唯一标识
     * @param thingId 物品 ID
     * @return
     */
    public boolean bindThingAndCert(String certArn, String thingId) {
        AttachThingPrincipalRequest request = AttachThingPrincipalRequest.builder()
                .thingName(thingId)
                .principal(certArn).build();
        AttachThingPrincipalResponse response = iotClient.attachThingPrincipal(request);
        return response.sdkHttpResponse().isSuccessful();
    }

    /**
     * 创建策略
     *
     * @param policyName 策略名称
     * @param policyContent 策略内容(json 格式)
     * @return
     */
    public boolean createPolicy(String policyName, String policyContent) {
        CreatePolicyRequest request = CreatePolicyRequest.builder()
                .policyName(policyName)
                .policyDocument(policyContent).build();
        CreatePolicyResponse response = iotClient.createPolicy(request);
        return response.sdkHttpResponse().isSuccessful();
    }

    /**
     * 绑定证书与策略
     *
     * @param certArn
     * @param policyName
     * @return
     */
    public boolean bindCertAndPolicy(String certArn, String policyName) {
        AttachPolicyRequest request = AttachPolicyRequest.builder()
                .policyName(policyName)
                .target(certArn)
                .build();
        AttachPolicyResponse response = iotClient.attachPolicy(request);
        return response.sdkHttpResponse().isSuccessful();
    }

    /**
     * 更新策略
     *
     * @param policyName
     * @param policyContent
     * @return
     */
    public boolean updatePolicy(String policyName, String policyContent) {
        // 查询策略的所有版本
        ListPolicyVersionsRequest listPolicyVersionsRequest = ListPolicyVersionsRequest.builder()
                .policyName(policyName).build();
        ListPolicyVersionsResponse listPolicyVersionsResponse = iotClient.listPolicyVersions(listPolicyVersionsRequest);
        if (!listPolicyVersionsResponse.sdkHttpResponse().isSuccessful()) {
            log.warn("删除策略失败,查询策略列表出错");
            return false;
        }
        if (CollectionUtils.isEmpty(listPolicyVersionsResponse.policyVersions())) {
            log.warn("删除策略失败,策略列表为空");
            return false;
        }
        // 删除非活跃版本
        listPolicyVersionsResponse.policyVersions().forEach(version -> {
            if (!version.isDefaultVersion()) {
                DeletePolicyVersionRequest deletePolicyVersionRequest = DeletePolicyVersionRequest.builder()
                        .policyName(policyName)
                        .policyVersionId(version.versionId()).build();
                iotClient.deletePolicyVersion(deletePolicyVersionRequest);
            }
        });
        // 创建策略版本并设置为活跃状态
        CreatePolicyVersionRequest request = CreatePolicyVersionRequest.builder()
                .policyName(policyName)
                .policyDocument(policyContent)
                .setAsDefault(true).build();
        CreatePolicyVersionResponse response = iotClient.createPolicyVersion(request);
        return response.sdkHttpResponse().isSuccessful();
    }



}
