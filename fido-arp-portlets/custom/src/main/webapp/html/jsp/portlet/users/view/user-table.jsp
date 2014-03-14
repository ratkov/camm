<%@include file="../../init.jsp"%>

<liferay-ui:search-container emptyResultsMessage="No users were found" delta="10"
        searchContainer="${userSearchContainer}">

    <liferay-ui:search-container-results results="<%=searchContainer.getResults() %>"
                                         total="<%=searchContainer.getTotal() %>"/>

    <liferay-ui:search-container-row
            className="com.liferay.portal.model.User"
            modelVar="user"
            >

        <liferay-ui:search-container-column-text
                name="user.name"
                value="${user.fullName}"
                />

        <liferay-ui:search-container-column-text
                name="user.login"
                value="${user.login}"
                />

        <liferay-ui:search-container-column-text
                name="user.email"
                value="${user.emailAddress}"
                />

        <portlet:actionURL name="readSettlement" var="readURL">
            <liferay-ui:search-container-column-text
                    name="user.status"
                    value="${user.status}"
                    href="javascript:void(0);"
                    />
        </portlet:actionURL>

        <%--TODO Here will be link which will generate new password for user by click--%>
        <liferay-ui:search-container-column-text
                        name="user.password.recovery"
                        value="${user.password}"
                       />

    </liferay-ui:search-container-row>

    <!-- Iterating the Results -->
    <liferay-ui:search-iterator/>

</liferay-ui:search-container>