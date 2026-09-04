package com.automation.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws Exception {
        String path = "src/test/resources/testdata/LoginData.xlsx";
        ExcelUtils.setExcelFile(path, "Sheet1");

        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount - 1][2];

        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = ExcelUtils.getCellData(i, 0); // username
            data[i - 1][1] = ExcelUtils.getCellData(i, 1); // password
        }
        return data;
    }

    @DataProvider(name = "CheckoutData")
    public Object[][] getCheckoutData() throws Exception {
        String path = "src/test/resources/testdata/CheckoutData.xlsx";
        ExcelUtils.setExcelFile(path, "Sheet1");

        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount - 1][3];

        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = ExcelUtils.getCellData(i, 0); // firstName
            data[i - 1][1] = ExcelUtils.getCellData(i, 1); // lastName
            data[i - 1][2] = ExcelUtils.getCellData(i, 2); // postalCode
        }
        return data;
    }
}