<%@include file="../init-common.jsp" %>

<liferay-util:html-bottom>

    <script type="text/javascript">

        jQuery(document).ready(function () {

            var errors = {
                "firstNameRequired": "<liferay-ui:message key="user.validation.first.name.required"/>",
                "firstNameRegexp": "<liferay-ui:message key="user.validation.first.name.failure"/>",

                "middleNameRequired": "<liferay-ui:message key="user.validation.middle.name.required"/>",
                "middleNameRegexp": "<liferay-ui:message key="user.validation.middle.name.failure"/>",

                "lastNameRequired": "<liferay-ui:message key="user.validation.last.name.required"/>",
                "lastNameRegexp": "<liferay-ui:message key="user.validation.last.name.failure"/>",

                "nameMinLength": "<liferay-ui:message key="user.validation.full.name.min.length"/>",
                "nameMaxLength": "<liferay-ui:message key="user.validation.full.name.max.length"/>",

                "loginRequired" : "<liferay-ui:message key="user.validation.login.required"/>",
                "loginMinLength" : "<liferay-ui:message key="user.validation.login.min.length"/>",
                "loginMaxLength" : "<liferay-ui:message key="user.validation.login.max.length"/>",
                "loginRegexp" : "<liferay-ui:message key="user.validation.wrong.format"/>",

                "emailRequired": "<liferay-ui:message key="user.validation.email.required"/>",
                "emailFormatError": "<liferay-ui:message key="user.validation.email.wrong.format"/>"
            };

            window.page = new ValidationProcessor(errors);
        });

    </script>

</liferay-util:html-bottom>

<form role="form" id="<portlet:namespace/>usersForm" action="${usersUrl}" method="POST" class="userContent">
    <div>
        <label id="<portlet:namespace/>addUserError"></label>
    </div>

    <input type="hidden" id="userId" name="userId" value="${currentUser.userId}"/>

    <div class="form-group">
        <label for="<portlet:namespace/>firstName">
            <liferay-ui:message key="user.first.name"/>&#58;&nbsp;&nbsp;*
        </label>

        <div>
            <input class="form-control required" id="<portlet:namespace/>firstName" type="text" name="firstName"
                   value="${currentUser.firstName}">
        </div>
    </div>
    <div class="form-group">
        <label for="<portlet:namespace/>middleName">
            <liferay-ui:message key="user.middle.name"/>&#58;&nbsp;&nbsp;*
        </label>

        <div>
            <input id="<portlet:namespace/>middleName" type="text" class="form-control required" name="middleName"
                   value="${currentUser.middleName}">
        </div>
    </div>
    <div class="form-group">
        <label for="<portlet:namespace/>lastName">
            <liferay-ui:message key="user.last.name"/>&#58;&nbsp;&nbsp;*
        </label>

        <div>
            <input id="<portlet:namespace/>lastName" type="text" class="form-control required" name="lastName"
                   value="${currentUser.lastName}">
        </div>
    </div>
    <div class="form-group">
        <label for="<portlet:namespace/>login">
            <liferay-ui:message key="user.login"/>&#58;&nbsp;&nbsp;*
        </label>

        <div>
            <input id="<portlet:namespace/>login" type="text" name="login" class="form-control required"
                   value="${currentUser.screenName}">
        </div>
    </div>
    <div class="form-group">
        <label for="<portlet:namespace/>email">
            <liferay-ui:message key="user.email"/>&#58;&nbsp;&nbsp;*
        </label>

        <div>
            <input id="<portlet:namespace/>email" type="text" name="email" class="form-control required"
                   value="${currentUser.emailAddress}">
        </div>
    </div>
    <div class="form-group">
        <label>
            <liferay-ui:message key="user.partners"/>&#58;&nbsp;&nbsp;
        </label>

        <div>
            <select name="partnerId" class="form-control">
                <c:forEach items="${partners}" var="partner">
                    <option value="${partner.organizationId}">
                            ${partner.name}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group">
        <div>
            <a href="javascript:void(0);" id="<portlet:namespace/>addUser" class="btn btn-primary"><liferay-ui:message key="user.save"/></a>
            <a href="${mainViewUrl}" class="btn btn-primary"><liferay-ui:message key="user.cancel"/></a>
        </div>
    </div>
</form>
