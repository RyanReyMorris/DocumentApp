<#include "security.ftlh">
<#import "login.ftlh" as l>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">DocumentTestApp</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <#if cooperator??>
                <li class="nav-item">
                    <a class="nav-link" href="/main">Все поручения</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/cooperator-orders/${currentCooperatorId}">Мои поручения</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/cooperator-other-orders/${currentCooperatorId}">Поручения мне</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/cooperator">Список пользователей</a>
                </li>
            </#if>
            <#if cooperator??>
                <li class="nav-item">
                    <a class="nav-link" href="/cooperator/profile">Мой профиль</a>
                </li>
            </#if>
        </ul>

        <div class="navbar-text mr-3">${name}</div>
        <@l.logout />
    </div>
</nav>