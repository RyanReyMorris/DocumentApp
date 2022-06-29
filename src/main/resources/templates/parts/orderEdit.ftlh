<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Создать поручение
</a>
<div class="collapse <#if order??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="subject" placeholder="Предмет поручения" required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       name="performer" placeholder="Исполнитель поручения" required>
            </div>
            <div class="form-group">
                <label for="deadline">Срок исполнения:
                    <input id="deadline" type="date" class="form-control" name="deadline">
                </label>
            </div>

            <div class="form-group">
                <label>Признак контрольности: <input type="checkbox"  name="controlling"></label>
<#--                <input type="text" class="form-control" name="controlling" placeholder="Признак контрольности">-->
            </div>
            <div class="form-group">
                <label>Признак исполнения: <input type="checkbox"  name="performing"></label>
<#--                <input type="text" class="form-control" name="performing" placeholder="Признак исполнения">-->
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="text" placeholder="Текст поручения:" required>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Выберите файл</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="<#if order??>${order.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Сохранить поручение</button>
            </div>
        </form>
    </div>
</div>