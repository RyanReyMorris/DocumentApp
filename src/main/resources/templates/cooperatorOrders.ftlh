<#import "parts/common.ftlh" as c>

<@c.page>
    <#if isCurrentCooperator>
        <#include "parts/orderEdit.ftlh" />
    </#if>

    <#include "parts/orderList.ftlh" />
</@c.page>