<%@include file="../init.jsp"%>

<liferay-util:html-bottom>

    <script type="text/javascript">

        jQuery(document).ready(function () {
            window.page = new UsersProcessor(
                    '<portlet:namespace/>');

            var errors = {
                "fullNameRequired": "<liferay-ui:message key="user.validation.full.name.required"/>",
                "nameMinLength": "<liferay-ui:message key="user.validation.full.name.min.length"/>",
                "nameMaxLength": "<liferay-ui:message key="user.validation.full.name.max.length"/>",

                "loginRequired" : "<liferay-ui:message key="user.validation.login.required"/>",
                "loginMinLength" : "<liferay-ui:message key="user.validation.login.min.length"/>",
                "loginMaxLength" : "<liferay-ui:message key="user.validation.login.max.length"/>",

                "emailRequired": "<liferay-ui:message key="user.validation.email.required"/>",
                "emailFormatError": "<liferay-ui:message key="user.validation.email.wrong.format"/>"
            };

            window.page = new ValidationProcessor(errors);
        });

    </script>

</liferay-util:html-bottom>