<%@include file="../init-common.jsp"%>

<input type="hidden" id="queryFormEdit" value="${queryFormEdit}"/>
<input type="hidden" id="paginator" value="${paginator}"/>
<div class="navbar">
    <div class="navbar-left">
        <a id="createNewQuery" href="javascript:void(0);" class="btn btn-primary"><liferay-ui:message key="queues.create.new.query"/></a>
    </div>
    <div class="navbar-right">
        <a href="javascript:void(0);" class="btn btn-info"><liferay-ui:message key="queues.export.to.excel"/></a>
        <a href="javascript:void(0);" id="showSearch" class="btn btn-default" data-show="search-box"><liferay-ui:message key="queues.search"/></a>
    </div>
</div>
<div class="panel-search <c:if test="${isSearch}">show</c:if>" id="search-box">
    <c:import url="search.jsp"/>
</div>

<div class="panel panel-default">
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
        <c:forEach items="${apps}" var="app">
            <tr>
                <td><a href="javascript:void(0);" class="edit-query">${app.appId}</a></td>
                <td>${app.createdDate}</td>
                <td>${app.clientName}</td>
                <td>${app.clientOkpo}</td>
                <td>${app.contactPhone}</td>
                <td>${app.creditAmount}</td>
                <td>${app.status}</td>
                <td>${app.comments}</td>
                <td>${app.user}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${pageCount gt 1}">
        <div class="panel-footer">
            <ul class="pagination">
                <li <c:if test="${(cpage - 1) eq 0}">class="disabled"</c:if>>
                    <a href="#" data-page="${cpage - 1}">&laquo;</a></li>
                <c:forEach begin="1" end="${pageCount}" var="page" step="1">
                    <li <c:if test="${page eq cpage}">class="active"</c:if>>
                        <a href="#" data-page="${page}">${page}</a></li>
                </c:forEach>
                <li <c:if test="${cpage eq pageCount}">class="disabled"</c:if>>
                    <a href="#" data-page="${cpage + 1}">&raquo;</a></li>
            </ul>
        </div>
    </c:if>

    <input type="hidden" id="cpage" value="${cpage}"/>

</div>

<div id="dialog" title="<liferay-ui:message key="queues.select.template"/>"></div>

<script type="text/x-handlebars-template" id="form-select-templates">
    <div>
        <form action="${queryFormAdd}" method="post">
           <input type="hidden" name="cpage" value="${cpage}"/>
           <c:forEach items="${productTypes}" var="productType">
               <div>
                   <input type="radio" value="${productType.productTypeId}" name="selectedProduct"
                          class="pretRad" data-label="${productType.getName(locale)}"/>
               </div>
           </c:forEach>
           <div>
               <label><liferay-ui:message key="queues.query.status"/></label>
                <select  name="selectedAppStatus">
                <c:forEach items="${appStatuses}" var="appStatus">
                   <option value="${appStatus.appStatusId}">${appStatus.getName(locale)}</option>
                </c:forEach>
                </select>
           </div>
           <div class="text-right">
                <input type="submit" class="btn btn-primary" value='<liferay-ui:message key="global.ok"/>' />
            </div>
        </form>
    </div>
</script>