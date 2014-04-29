<%@include file="../init-common.jsp"%>

<form role="form" class="form-horizontal" action="${search}" method="post">
    <input type="hidden" name="isSearch" id="isSearch" value="${isSearch}" />
    <input type="hidden" name="partner" id="partner" value="${partner}" />
    <div class="row">
        <div class="col-md-4">
            <div class="form-group">
                <label class="control-label col-sm-4"><liferay-ui:message key="queues.query.id"/></label>
                <div class="col-sm-8">
                    <input type="text" id="searchId" name="searchId" value="${mapSearch.searchId != 0 ? mapSearch.searchId : ''}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4"><liferay-ui:message key="queues.query.date"/></label>
                <div class="col-sm-8">
                    <input type="text" id="from" name="searchDateStart" value="${mapSearch.searchDateStart}" placeholder="<liferay-ui:message key='queues.start'/>" />
                    <input type="text" id="to" name="searchDateEnd" value="${mapSearch.searchDateEnd}"  placeholder="<liferay-ui:message key='queues.end'/>" />
                </div>
            </div>
            <div class="form-group">
                <label  class="control-label col-sm-4"><liferay-ui:message key="queues.client.name"/></label>
                <div class="col-sm-8">
                    <input type="text" id="searchName" name="searchName" value="${mapSearch.searchName}" />
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <label  class="control-label col-sm-4"><liferay-ui:message key="queues.personal.number.client"/></label>
                <div class="col-sm-8">
                    <input type="text" id="searchOkpo" name="searchOkpo" value="${mapSearch.searchOkpo}" />
                </div>
            </div>
            <div class="form-group">
                <label  class="control-label col-sm-4"><liferay-ui:message key="queues.contact.phone"/></label>
                <div class="col-sm-8">
                    <input type="text" id="searchPhone" name="searchPhone" value="${mapSearch.searchPhone}"  />
                </div>
            </div>
            <div class="form-group">
                <label  class="control-label col-sm-4"><liferay-ui:message key="queues.agreed.sum"/></label>
                <div class="col-sm-8">
                    <input type="text" id="searchSum" name="searchSum" value="${mapSearch.searchSum != 0 ? mapSearch.searchSum : ''}"/>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <label  class="control-label col-sm-4"><liferay-ui:message key="queues.query.status"/></label>
                <div class="col-sm-8">
                    <select id="searchAppStatus" name="searchAppStatus">
                        <option value="0">---</option>
                        <c:forEach items="${appStatuses}" var="appStatus">
                            <option value="${appStatus.appStatusId}"
                                    <c:if test="${appStatus.appStatusId eq mapSearch.searchAppStatus}">selected="true"</c:if>>${appStatus.getName(locale)}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label  class="control-label col-sm-4"><liferay-ui:message key="queues.comment"/></label>
                <div class="col-sm-8">
                    <input type="text" id="searchComment" name="searchComment" value="${mapSearch.searchComment}"/>
                </div>
            </div>
            <c:if test="${isAdmin}">
            <div class="form-group">
                <label  class="control-label col-sm-4"><liferay-ui:message key="queues.user"/></label>
                <div class="col-sm-8">
                    <select id="searchUser" name="searchUser">
                        <option value="0">---</option>
                        <c:forEach items="${users}" var="user">
                            <option value="${user.userId}"
                                    <c:if test="${user.userId eq mapSearch.searchUser}">selected="true"</c:if>>${user.screenName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            </c:if>
            <div class="form-group text-right">
                <button class="btn btn-default"><liferay-ui:message key="queues.search"/></button>
            </div>
        </div>
    </div>

</form>