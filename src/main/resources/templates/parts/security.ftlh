<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    cooperator = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = cooperator.getUsername()
    isAdmin = cooperator.isAdmin()
    currentCooperatorId = cooperator.getId()
    >
<#else>
    <#assign
    name = ""
    isAdmin = false
    currentCooperatorId = -1
    >
</#if>