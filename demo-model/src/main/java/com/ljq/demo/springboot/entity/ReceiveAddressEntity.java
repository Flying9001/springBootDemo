package com.ljq.demo.springboot.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 收货地址实体类
 *
 * @author junqiang.lu
 * @date 2019-06-16 16:09:06
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiveAddressEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

    /**
	 * id,主键
	 * */
	private Long id;
    /**
	 * 用户 id
	 * */
	private Long userId;
    /**
	 * 收货人姓名
	 * */
	private String receiverName;
    /**
	 * 收货人电话,支持手机号和座机
	 * */
	private String telephone;
    /**
	 * 省份
	 * */
	private String province;
    /**
	 * 城市
	 * */
	private String city;
    /**
	 * 城市地区,县
	 * */
	private String area;
    /**
	 * 详细地址
	 * */
	private String address;
    /**
	 * 是否为默认地址,true是;false不是(默认值)
	 * */
	private Integer defaultAddress;


}
