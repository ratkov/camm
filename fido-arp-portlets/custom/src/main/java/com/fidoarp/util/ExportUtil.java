package com.fidoarp.util;

import com.fidoarp.catalog.model.App;
import com.fidoarp.catalog.model.AppStatus;
import com.fidoarp.catalog.service.AppStatusLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ExportUtil {
    private static Log log = LogFactoryUtil.getLog(ExportUtil.class);

    public Workbook getAppExcel(List<App> apps, String sheetName, Locale locale){
        try {
            Workbook wb = new HSSFWorkbook();
            Sheet queueSheet = wb.createSheet(sheetName);

            addHeaderRow(locale, queueSheet, wb.createFont());

            int row = 1;
            if(!apps.isEmpty()) {
                for(App app: apps){
                    addAppRow(locale, queueSheet, row, app, wb.createFont());
                    row = row + 1;
                }
            }
            return wb;
        }catch (Exception e){
            log.error("Error in ExportUtil.getAppExcel(): " + Arrays.toString(e.getStackTrace()), e);
        }
        return null;
    }

    private void addAppRow(Locale locale, Sheet queueSheet, int row, App app, Font font) {
        try {
            Row dataRow = queueSheet.createRow(row);
            newExcelCell(dataRow, 0, String.valueOf(app.getAppId()));
            newExcelCell(dataRow, 1, String.valueOf(app.getCreatedDate()));
            newExcelCell(dataRow, 2, app.getClientName());
            newExcelCell(dataRow, 3, app.getClientOkpo());
            newExcelCell(dataRow, 4, app.getContactPhone());
            newExcelCell(dataRow, 5, String.valueOf(app.getCreditAmount()));
            String statusName = "";
            if (app.getStatusId() != 0) {
                AppStatus appStatus = AppStatusLocalServiceUtil.getAppStatus(app.getStatusId());
                statusName = appStatus.getName(locale);
            }
            newExcelCell(dataRow, 6, statusName);
            newExcelCell(dataRow, 7, app.getComments(locale));
            String userName = "";
            if (app.getUserId() != 0) {
                User user = UserLocalServiceUtil.getUser(app.getUserId());
                userName = user.getScreenName();
            }
            newExcelCell(dataRow, 8, userName);
        }catch (Exception e){
            log.error("Error in ExportUtil.addAppRow(): " + Arrays.toString(e.getStackTrace()), e);
        }
    }

    private void addHeaderRow(Locale locale, Sheet queueSheet, Font font) {
        ResourceBundle res =  new FidoARPUtils().getResourceBundle("/i18n/Resource", locale, "UTF-8");
        Row headerRow = queueSheet.createRow(0);
        newExcelCell(headerRow, 0, res.getString("queues.query.id"));
        newExcelCell(headerRow, 1, res.getString("queues.query.date"));
        newExcelCell(headerRow, 2, res.getString("queues.client.name"));
        newExcelCell(headerRow, 3, res.getString("queues.personal.number.client"));
        newExcelCell(headerRow, 4, res.getString("queues.contact.phone"));
        newExcelCell(headerRow, 5, res.getString("queues.agreed.sum"));
        newExcelCell(headerRow, 6, res.getString("queues.query.status"));
        newExcelCell(headerRow, 7, res.getString("queues.comment"));
        newExcelCell(headerRow, 8, res.getString("queues.user"));
    }

    public void newExcelCell(Row row, int counter, String value){
        Cell cell = row.createCell(counter);
        cell.setCellValue(value);
    }

}
