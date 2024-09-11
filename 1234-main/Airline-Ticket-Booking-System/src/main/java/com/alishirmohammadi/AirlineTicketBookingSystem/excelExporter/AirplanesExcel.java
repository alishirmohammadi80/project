package com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airlines;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airplanes;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class AirplanesExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Airplanes> listAirplanes;

    public AirplanesExcel(List<Airplanes> listAirplanes) {
        this.listAirplanes = listAirplanes;
        workbook = new XSSFWorkbook();
    }
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Airplanes");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "نام هواپیما", style);
        createCell(row, 2, "کد هواپیما", style);
        createCell(row, 3, "ظرفیت", style);
        createCell(row, 4, "ایرلاین", style);
    }
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    private void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Airplanes airplanes : listAirplanes) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++,  airplanes.getAirplaneId(), style);
            createCell(row, columnCount++, airplanes.getAirplaneName(), style);
            createCell(row, columnCount++, airplanes.getAirplaneCode(), style);
            createCell(row, columnCount++,airplanes.getCapacity(), style);
            createCell(row, columnCount++,airplanes.getAirlines(), style);
        }
}
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}