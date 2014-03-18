<%@include file="../init-common.jsp"%>

<div class="navbar">
    <div class="navbar-left">
        <a id="createNewQuery" href="javascript:void(0);" class="btn btn-primary"><liferay-ui:message key="queues.create.new.query"/></a>
    </div>
    <div class="navbar-right">
        <a href="javascript:void(0);" class="btn btn-info"><liferay-ui:message key="queues.export.to.excel"/></a>
        <a href="javascript:void(0);" class="btn btn-default"><liferay-ui:message key="queues.search"/></a>
    </div>
</div>
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading">10.08.13-01.09.13</div>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th><liferay-ui:message key="queues.query.id"/></th>
            <th><liferay-ui:message key="queues.query.date"/></th>
            <th><liferay-ui:message key="queues.client.name"/></th>
            <th><liferay-ui:message key="queues.personal.number.client"/></th>
            <th><liferay-ui:message key="queues.contact.phone"/></th>
            <th><liferay-ui:message key="queues.agreed.sum"/></th>
            <th><liferay-ui:message key="queues.query.status"/></th>
            <th><liferay-ui:message key="queues.comment"/></th>
            <th><liferay-ui:message key="queues.user"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>

<div id="dialog" title="<liferay-ui:message key="queues.select.template"/>"></div>

<script type="text/x-handlebars-template" id="form-select-templates">
    <div>
        <form action="${createNewQuery}" method="post">
           <c:forEach items="${ddmTemplates}" var="template">
               <input type="radio" value="${template.templateId}" name="selectedTemplate" />
            <label>${template.name}</label>
           </c:forEach>
           <div class="text-right">
                <input type="submit" class="btn btn-primary" value='<liferay-ui:message key="global.ok"/>' />
            </div>
        </form>
    </div>
</script>