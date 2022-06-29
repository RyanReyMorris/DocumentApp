<#include "security.ftlh">
<#import "pager.ftlh" as p>

<@p.pager url page/>
<div class="card-columns">
    <#list page.content as order>
        <div class="card my-3">
            <#if order.filename??>
                <img src="/img/${order.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <b>Предмет поручения: ${order.subject}</b><br/>
                <span>Исполнитель: ${order.performer}</span><br/>
                <span>Срок исполнения: ${order.deadline}</span><br/>
                <span>Признак контрольности: ${order.controlling}</span><br/>
                <span>Признак исполнения: ${order.performing}</span><br/>
                <span>Текст поручения: ${order.text}</span><br/>
            </div>
            <div class="card-footer text-muted">
                <strong>Автор: ${order.authorName}</strong>
            </div>
            <div class="card-footer text-muted">
                <#if order.author.id == currentCooperatorId>
                    <a class="btn btn-primary" href="/cooperator-orders/${order.author.id}?order=${order.id}">
                        Изменить
                    </a>
                    <a class="btn btn-warning" href="/del-cooperator-orders/${order.author.id}?order=${order.id}">
                        Удалить
                    </a>
                </#if>
            </div>
        </div>
    <#else>
        Отсутствуют поручения
    </#list>
</div>

<@p.pager url page/>