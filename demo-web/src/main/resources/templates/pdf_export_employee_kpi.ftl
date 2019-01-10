<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Title</title>
    <style>
        @page {
            size: 279mm 216mm;
        }
        table{
            margin: auto;
            border: 1px solid #333;
            border-bottom: none;
            border-left: none;
            font-family: SimSun;
        }
        td{
            height: 30px;
            border: 1px solid #333;
            border-top: none;
            text-align: center;
            position: relative;
        }
        tr.title{
            font-weight: bold;
        }
        td.title{
            height: 50px;
            font-weight: bold;
        }
        td.value{
            color: blue;
        }
        td.content{
            font-size: 12px;
            text-align: left;
        }
        td.sign{
            text-align: left;
            height: 40px;
        }
    </style>

</head>
<body>

<table class="table" cellspacing="0">

    <tr >
        <td class="title" colspan="10">
            项目成员绩效考核表
        </td>
    </tr>

    <tr >
        <td style="width: 10%;">
            被考核者
        </td>
        <td class="value" style="width: 10%;">
           <#if employee??>
            <#if employee.name??>
                ${employee.name}
            </#if>
           </#if>
        </td>
        <td style="width: 10%;">
            部门
        </td>
        <td colspan="2" class="value" style="width: 20%;">
             <#if employeeDepart??>
                <#if employeeDepart.name??>
                    ${employeeDepart.name}
                </#if>
             </#if>
        </td>
        <td style="width: 10%;">
            考核者
        </td>
        <td class="value" style="width: 10%;">
             <#if acceptUser??>
                <#if acceptUser.name??>
                    ${acceptUser.name}
                </#if>
             </#if>
        </td>
        <td style="width: 10%;">
            考核时间
        </td>
        <td colspan="2" class="value" style="width: 20%;">
             <#if statisticalTime??>
                 ${statisticalTime}
             </#if>
        </td>
    </tr>

    <tr >
        <td colspan="10">
            第一部分工作目标(权重80%)
        </td>
    </tr>

    <tr class="title">
        <td colspan="2">
            指标名称
        </td>
        <td >
            权重
        </td>
        <td colspan="3">
            指标定义与评分标准
        </td>
        <td >
            完成值
        </td>
        <td >
            数据提供部门/人
        </td>
        <td >
            自评得分
        </td>
        <td >
            上级评分
        </td>
    </tr>

    <tr >
        <td colspan="2">
            工作计划完成率
        </td>
        <td >
            30%
        </td>
        <td colspan="3" class="content">
            实际完成量/计划完成量*100%<br/>
            1.完成比≥100%，本项为满分;<br/>
            2.完成比在90%（含）-100%（不含），扣10分；<br/>
            3.完成比在80%（含）-90%（不含），扣20分；<br/>
            4.完成比在80%（不含）以下的，本项为0分
        </td>
        <td class="value">
            <#if jobCompletionRate??>
                ${jobCompletionRate*100}%
            </#if>
        </td>
        <td >
            项目经理
        </td>
        <td class="value">
            <#if jobCompletionRateScore??>
                ${jobCompletionRateScore}
            </#if>
        </td>
        <td class="value">
             <#if jobCompletionRateSuperiorScore??>
                 ${jobCompletionRateSuperiorScore}
             </#if>
        </td>
    </tr>

    <tr >
        <td colspan="2">
            工作计划完成及时率
        </td>
        <td >
            25%
        </td>
        <td colspan="3" class="content">
            实际完成天数/计划完成天数*100%<br/>
            1.完成比≦100%，本项为满分；<br/>
            2.完成比在100%-110%（不含），扣5分；<br/>
            3.完成比在110%（含）-130%（不含）扣10分；<br/>
            4.完成比在130%（含）以上的，本项为0分
        </td>
        <td class="value">
            <#if finishRate??>
                ${finishRate*100}%
            </#if>
        </td>
        <td >
            项目经理
        </td>
        <td class="value">
            <#if finishRateScore??>
                ${finishRateScore}
            </#if>
        </td>
        <td class="value">
             <#if finishRateSuperiorScore??>
                 ${finishRateSuperiorScore}
             </#if>
        </td>
    </tr>

    <tr >
        <td colspan="2">
            返工率
        </td>
        <td >
            20%
        </td>
        <td colspan="3" class="content">
            实际返工次数/计划返工次数*100%<br/>
            1.完成比≤100%，本项为满分；<br/>
            2.完成比在100%（不含）-110%（不含），扣10分；<br/>
            3.完成比在110%（不含）-120%（含），扣15分；<br/>
            4.完成比在120%（不含）以上的，本项为0分
        </td>
        <td class="value">
            <#if returnRate??>
                ${returnRate*100}%
            </#if>
        </td>
        <td >
            项目经理<br/>
            外型供应商
        </td>
        <td class="value">
            <#if returnRateScore??>
                ${returnRateScore}
            </#if>
        </td>
        <td class="value">
             <#if returnRateSuperiorScore??>
                 ${returnRateSuperiorScore}
             </#if>
        </td>
    </tr>

    <tr >
        <td colspan="2">
            技术文档资料保存完整性
        </td>
        <td >
            15%
        </td>
        <td colspan="3" class="content">
            实际上传资料数/要求上传资料数*100%<br/>
            1.完成比100%，本项得满分；<br/>
            2.完成比＜100%，本项为0分
        </td>
        <td class="value">
            <#if uploadRate??>
                ${uploadRate*100}%
            </#if>
        </td>
        <td >
            项目经理
        </td>
        <td class="value">
            <#if uploadRateScore??>
                ${uploadRateScore}
            </#if>
        </td>
        <td class="value">
             <#if uploadRateSuperiorScore??>
                 ${uploadRateSuperiorScore}
             </#if>
        </td>
    </tr>

    <tr >
        <td colspan="2">
            满意度
        </td>
        <td >
            10%
        </td>
        <td colspan="3" class="content">
            及时参加项目例会、积极汇报工作<br/>
            总分10分<br/>
            每遗漏一次，扣4分；扣完为止
        </td>
        <td >
            --
        </td>
        <td >
            项目经理
        </td>
        <td class="value">
            <#if satisfactionScore??>
                ${satisfactionScore}
            </#if>
        </td>
        <td class="value">
             <#if satisfactionSuperiorScore??>
                 ${satisfactionSuperiorScore}
             </#if>
        </td>
    </tr>

    <tr >
        <td colspan="10">
            第二部分工作态度(权重20%)
        </td>
    </tr>

    <tr class="title">
        <td colspan="2">
            工作态度指标
        </td>
        <td>
            衡量方法
        </td>
        <td colspan="4">
            衡量标准
        </td>
        <td >
            权重
        </td>
        <td >
            自评得分
        </td>
        <td >
            上级评分
        </td>
    </tr>

    <tr >
        <td colspan="2">
            责任心
        </td>
        <td>
            上级评价
        </td>
        <td colspan="4" class="content">
            1.仅仅能按上级要求完成本职工作（5分）； <br/>
            2.能够严格按照工作标准完成工作目标对本职工作负责到底，<br/>
            工作中不推卸责任、不上交矛盾，失误较少（10分）； <br/>
            3.对待工作不怕繁琐、有耐心，考虑问题与做事细致、周到（15分）； <br/>
            4.对待工作精益求精，力求一次性做到完美（20分）； <br/>
            5.对团队成员拥有强烈的责任感，努力帮助团队成员提升工作质量（25分）
        </td>
        <td >
            25%
        </td>
        <td class="value">
    		<#if responsibilityScore??>
                ${responsibilityScore}
            </#if>
        </td>
        <td class="value">
    		<#if responsibilitySuperiorScore??>
                ${responsibilitySuperiorScore}
            </#if>
        </td>
    </tr>

    <tr >
        <td colspan="2">
            主动性
        </td>
        <td>
            上级评价
        </td>
        <td colspan="4" class="content">
            1.按上级安排/ 指示做事，安排什么做什么（5分）<br/>
            2.按自己的职位职责做事，工作任务大多能完成；<br/>
            同时对工作中出现的问题，也能被动反应，予以处理（10分）<br/>
            3.对自己的工作大致有思考，上级安排的任务能有效配合确定工作计划，<br/>
            并按计划完成工作任务；同时能积极处理工作中出现的各种问题，<br/>
            需要请示或上级支持时也能按程序办理（15分）<br/>
            4.提前思考、主动安排自己的工作计划，并将之主动与上级沟通、协商、确定，<br/>
            按计划推进、完成工作任务；对工作问题提前预防，并妥善处理各类问题；<br/>
            能积极主动地协助同事完成职责范围外的其他工作（20分）<br/>
            5.上级只给出一个方向或任务，既能独立地制定计划、组织资源、推进实施、保证完成，<br/>
            支持、鼓励团队成员与周围同事积极主动开展工作，<br/>
            能营造积极、主动的文化氛围（25）
        </td>
        <td >
            25%
        </td>
        <td class="value">
    		<#if initiativeScore??>
                ${initiativeScore}
            </#if>
        </td>
        <td class="value">
    		<#if initiativeSuperiorScore??>
                ${initiativeSuperiorScore}
            </#if>
        </td>
    </tr>

    <tr >
        <td colspan="2">
            团队合作
        </td>
        <td>
            上级评价
        </td>
        <td colspan="4" class="content">
            1.积极融入团队并乐于接受同事帮助，配合团队完成工作(5分)<br/>
            2.主动给予同事必要的帮助；碰到困难时，善于利用团队的力量解决问题(10分)<br/>
            3.决策前积极发表个人意见，充分参与团队讨论；决策后，个人无论是否有异议，<br/>
            必须从行动上完全予以支持（15分）；<br/>
            4.能够客观认识同事的优缺点，并在工作中充分体现“对事不对人”的原则(20分)<br/>
            5.能够以积极正面的心态去影响团队，并改善团队表现和氛围（25分）<br/>
        </td>
        <td >
            25%
        </td>
        <td class="value">
    		<#if teamCooperationScore??>
                ${teamCooperationScore}
            </#if>
        </td>
        <td class="value">
    		<#if teamCooperationSuperiorScore??>
                ${teamCooperationSuperiorScore}
            </#if>
        </td>
    </tr>
    <tr >
        <td colspan="2">
            保密意识
        </td>
        <td>
            上级评价
        </td>
        <td colspan="4" class="content">
            1.对岗位的保密责任有一定的认识（5分）；<br/>
            2.熟悉公司保密协议，明确职责范围内的保密事项，并采取相应的维护措施(10分)<br/>
            3.以身作则，自觉、严格遵守保密协议，对保密协议未明确界定的问题能够很好的处理(15分)<br/>
            4.影响身边的同事，宣传保密意识，随时提醒同事；发现保密协议的缺陷和漏洞<br/>
            能及时向有关部门报告，并提出完善建议（20分）；<br/>
            5.获悉他人违反和破坏保密协议时，积极抵制，能够及时向公司有关部门报告，<br/>
            并分情况采取积极措施以最大限度减少恶性后果，处理得当（25分）
        </td>
        <td >
            25%
        </td>
        <td class="value">
    		<#if secrecyScore??>
                ${secrecyScore}
            </#if>
        </td>
        <td class="value">
    		<#if secrecySuperiorScore??>
                ${secrecySuperiorScore}
            </#if>
        </td>
    </tr>

    <tr >
        <td colspan="8">
            合计
        </td>
        <td class="value">
    		<#if totalScore??>
                ${totalScore}
            </#if>
        </td>
        <td class="value">
    		<#if totalSuperiorScore??>
                ${totalSuperiorScore}
            </#if>
        </td>
    </tr>

    <tr >
        <td colspan="2">
            等级评定规则
        </td>
        <td class="content" colspan="5">
            A：优秀（100分以上）<br/>
            B：良好（90-100分）<br/>
            C：合格（80-90分）<br/>
            D：基本合格（70-80分）<br/>
            E:需改进（60-70分）<br/>
            F：不合格（60分以下）
        </td>
        <td >
            等级评定
        </td>
        <td class="value" colspan="2">
            <#if grade??>
                ${grade}
            </#if>
        </td>
    </tr>

    <tr style="height: 100px;" >
        <td colspan="2">
            自我总结
        </td>
        <td colspan="8" class="content value">
    		<#if selfSummary??>
                ${selfSummary}
            </#if>
        </td>
    </tr>

    <tr >
        <td rowspan="2" colspan="2">
            考核结果确认
        </td>
        <td class="sign" colspan="4">
            考核者签名:
        </td>
        <td class="sign" colspan="4">
            日期:
        </td>
    </tr>
    <tr>
        <td class="sign" colspan="4">
            被考核者签名:
        </td>
        <td class="sign" colspan="4">
            日期:
        </td>
    </tr>

</table>


</body>
</html>