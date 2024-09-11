package com.alishirmohammadi.AirlineTicketBookingSystem.excelExporter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airlines;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Airplanes;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Customer;
import com.alishirmohammadi.AirlineTicketBookingSystem.entity.Flight;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class FlightExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Flight> listFlight;
    public FlightExcel(List<Flight> listFlight) {
        this.listFlight = listFlight;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Flights");
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
        createCell(row, 10, "تعداد صندلی موجود", style);
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
        for (Flight flight : listFlight) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++,  flight.getId(), style);
            createCell(row, columnCount++, flight.getFlightNumber(), style);
            createCell(row, columnCount++, flight.getFare(), style);
            createCell(row, columnCount++,flight.getJourneyTime(), style);
            createCell(row, columnCount++,flight.getAirlines(), style);
            createCell(row, columnCount++,flight.getAirplanes(), style);
            createCell(row, columnCount++,flight.getSourceStop(), style);
            createCell(row, columnCount++,flight.getDestStop(), style);
            createCell(row, columnCount++,flight.getTripDate(), style);
            createCell(row, columnCount++,flight.getTripTime(), style);
            createCell(row, columnCount++,flight.getAvailableSeats(), style);
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
