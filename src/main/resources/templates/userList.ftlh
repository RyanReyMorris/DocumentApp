<#import "parts/common.ftlh" as c>

<@c.page>
    Список работников и их прав
    <table>
        <thead>
        <tr>
            <th>Фамилия</th>
            <th>Права</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list cooperators as cooperator>
            <tr>
                <td>${cooperator.username}</td>
                <td><#list cooperator.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/cooperator/${cooperator.id}">редактировать</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>