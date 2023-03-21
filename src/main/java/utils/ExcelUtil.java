package utils;

import model.Account;
import model.CalenderReward;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.DataService;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class ExcelUtil {
    public static final int COLUMN_INDEX_EMAIL = 0;
    public static final int COLUMN_INDEX_ADDRESS = 1;
    public static final int COLUMN_INDEX_PRIVATE_KEY = 2;
    public static final int COLUMN_INDEX_ID_GENERATE = 3;
    public static final int COLUMN_INDEX_CHD_ID = 4;
    public static final int COLUMN_INDEX_CLOUD = 5;
    public static final int COLUMN_INDEX_TOTAL_EARN = 1;
    public static final String FILE_EXCEL = "data.xlsx";
    public static final String SHEET_INFO = "account";
    public static final String SHEET_CLAIM = "claim";
    public static Workbook workbook;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        initExcel();
    }

    public static void initExcel() throws IOException, ClassNotFoundException {
        FileUtil.DATA_FILE = "data.bat";
        FileUtil.readData();
        List<String> accountList = FileUtil.readFile(FileUtil.ACCOUNT_FILE);
        workbook = createWorkBook(FILE_EXCEL);
        initClaimSheet(accountList, workbook);
        initAccountSheet(accountList, workbook);
        saveFile(workbook, FILE_EXCEL);
    }

    public static void initAccountSheet(List<String> accountList, Workbook workbook) {

        Sheet sheet = workbook.createSheet(SHEET_INFO);
        int rowIndex = 0;
        writeHeaderInfor(sheet, rowIndex++);
        for (String email : accountList) {
            Row row = sheet.createRow(rowIndex++);
            writeInforData(DataService.data.get(email), row);
        }
        int numberOfCol = sheet.getRow(0).getPhysicalNumberOfCells();
        autoResizeColumn(sheet, numberOfCol);

    }

    public static void initClaimSheet(List<String> accountList, Workbook workbook) {


        Sheet sheet = workbook.createSheet(SHEET_CLAIM);
        int rowIndex = 0;
        writeHeaderClaim(sheet, rowIndex++);
        for (String email : accountList) {
            Row row = sheet.createRow(rowIndex++);
            writeClaimData(DataService.data.get(email), row);
        }
        writeFooterClaim(sheet, rowIndex);
        int numberOfCol = sheet.getRow(0).getPhysicalNumberOfCells();
        autoResizeColumn(sheet, numberOfCol);
    }

    public static Workbook createWorkBook(String filePath) {
        Workbook workbook = null;

        if (filePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (filePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    private static CellStyle createStyleForHeader(Sheet sheet) {
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        font.setColor(IndexedColors.RED.getIndex());
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    public static void autoResizeColumn(Sheet sheet, int lastColumn) {
        for (int i = 0; i < lastColumn; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public static void saveFile(Workbook workbook, String pathFile) {
        try (OutputStream os = new FileOutputStream(pathFile);) {
            workbook.write(os);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Workbook getWorkBook(InputStream is, String pathFile) throws IOException {
        Workbook workbook = null;
        if (pathFile.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(is);
        } else if (pathFile.endsWith("xls")) {
            workbook = new HSSFWorkbook(is);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    public static Workbook readFile(String pathFile) throws IOException {
        InputStream is = new FileInputStream(pathFile);
        Workbook workbook = getWorkBook(is, pathFile);
        return workbook;
    }

    public static void writeInforData(Account account, Row row) {
        Cell cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellValue(account.getEmail());
        cell = row.createCell(COLUMN_INDEX_ADDRESS);
        cell.setCellValue(account.getWallet().getWalletAddress());
        cell = row.createCell(COLUMN_INDEX_PRIVATE_KEY);
        cell.setCellValue(account.getPrivateKey());
        cell = row.createCell(COLUMN_INDEX_ID_GENERATE);
        cell.setCellValue(account.getId_generator());
        cell = row.createCell(COLUMN_INDEX_CHD_ID);
        cell.setCellValue(account.getCdhPlayerId());
        cell = row.createCell(COLUMN_INDEX_CLOUD);
        cell.setCellValue(account.getLocalId());

    }

    public static void writeClaimData(Account account, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(account.getEmail());
        cell = row.createCell(1);
        cell.setCellValue(account.getTotalTowerTokensEarned());
        HashMap<String, CalenderReward> rewards = CommonUtils.sortCalenderReward(account.getCalenderInfor());

        for (String id : rewards.keySet()) {
            cell = row.createCell(row.getLastCellNum());
            cell.setCellValue(rewards.get(id).getTowerTokenEarned());
        }
        if (row.getLastCellNum() - 2 < CommonUtils.getDifferDate()) {
            cell = row.createCell(row.getLastCellNum());
            cell.setCellValue("Not chest");
        }
    }

    public static void updateClaimDate(Account account, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(account.getEmail());
        cell = row.createCell(1);
        cell.setCellValue(account.getTotalTowerTokensEarned());
    }

    public static void writeFooterClaim(Sheet sheet, int rowIndex) {
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(0);
        cell.setCellStyle(createStyleForHeader(sheet));
        cell.setCellValue("TOTAL");
        cell = row.createCell(COLUMN_INDEX_TOTAL_EARN);
        cell.setCellFormula("SUM(B1:B102)");
    }

    public static void writeHeaderInfor(Sheet sheet, int rowIntdex) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIntdex);
        Cell cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");
        cell = row.createCell(COLUMN_INDEX_ADDRESS);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Address");
        cell = row.createCell(COLUMN_INDEX_PRIVATE_KEY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("PrivateKey");
        cell = row.createCell(COLUMN_INDEX_ID_GENERATE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ID Generate");
        cell = row.createCell(COLUMN_INDEX_CHD_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("CHDID");
        cell = row.createCell(COLUMN_INDEX_CLOUD);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("CLOUD");
    }

    public static void writeHeaderClaim(Sheet sheet, int rowIntdex) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIntdex);
        Cell cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");
        cell = row.createCell(COLUMN_INDEX_TOTAL_EARN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Total Earn");
        for (int i = CommonUtils.getDifferDate(); i > 0; i--) {
            cell = row.createCell(row.getLastCellNum());
            cell.setCellStyle(cellStyle);
            cell.setCellValue(CommonUtils.subtract(i - 1));
        }
    }

    public static CellStyle getCellStyle() {
        CellStyle cellStyle = workbook.getSheet(SHEET_CLAIM).getWorkbook().createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }
}
