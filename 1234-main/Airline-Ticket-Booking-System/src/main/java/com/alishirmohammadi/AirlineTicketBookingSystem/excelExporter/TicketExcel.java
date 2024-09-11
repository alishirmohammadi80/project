package com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class TicketExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Ticket> listTicket;
    public TicketExcel(List<Ticket> listTicket) {
        this.listTicket = listTicket;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Tickets");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "شماره پرواز", style);
        createCell(row, 2, "هزینه", style);
        createCell(row, 3, "زمان پرواز", style);
        createCell(row, 4, "ایرلاین", style);
        createCell(row, 5, "هواپیما", style);
        createCell(row, 6, "مبدا", style);
        createCell(row, 7, "مقصد", style);
        createCell(row, 8, "تاریخ پرواز", style);
        createCell(row, 9, "ساعت پرواز", style);
        createCell(row, 10, "شماره صندلی", style);
        createCell(row, 11, "کدملی خریدار", style);
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
        for (Ticket ticket :listTicket) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++,  ticket.getId(), style);
            createCell(row, columnCount++, ticket.getFlight().getFlightNumber(), style);
            createCell(row, columnCount++, ticket.getFlight().getFare(), style);
            createCell(row, columnCount++,ticket.getFlight().getJourneyTime(), style);
            createCell(row, columnCount++,ticket.getFlight().getAirlines(), style);
            createCell(row, columnCount++,ticket.getFlight().getAirplanes(), style);
            createCell(row, columnCount++,ticket.getFlight().getSourceStop(), style);
            createCell(row, columnCount++,ticket.getFlight().getDestStop(), style);
            createCell(row, columnCount++,ticket.getFlight().getTripDate(), style);
            createCell(row, columnCount++,ticket.getFlight().getTripTime(), style);
            createCell(row, columnCount++,ticket.getSeatNumber(), style);
            createCell(row, columnCount++,ticket.getCustomer().getNationalCode(), style);
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



