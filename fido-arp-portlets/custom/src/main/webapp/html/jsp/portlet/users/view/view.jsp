<%@include file="../init-common.jsp"%>

<liferay-util:html-bottom>

    <script type="text/javascript">

        jQuery(document).ready(function () {
            window.page = new UsersProcessor('<portlet:namespace/>');

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

            $('#ok').button('option', 'label', '<liferay-ui:message key="status.change"/>');
            $('#cancel').button('option', 'label', '<liferay-ui:message key="status.cancel"/>');

            $('#change-password-ok').button('option', 'label', '<liferay-ui:message key="change.password.change"/>');
            $('#change-password-cancel').button('option', 'label', '<liferay-ui:message key="change.password.cancel"/>');
        });

    </script>

</liferay-util:html-bottom>

<div id="userWrapper">
    <c:import url="main-view.jsp" />
</div>