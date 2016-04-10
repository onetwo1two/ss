package pathfinder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


public class Parser {

    public static int[][] parse(String name) throws IOException {
        int matrix[][] = new int[0][0];
        try (InputStream inputStream = new FileInputStream(name);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            int lastCellNum = sheet.getRow(sheet.getLastRowNum()).getLastCellNum();
            int xDiff = 0;
            int yDiff = 0;

            Iterator<Row> rows = sheet.rowIterator();
            boolean inRange = false;
            while (rows.hasNext()) {
                Row row = rows.next();
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    int y = cell.getRowIndex() - yDiff;
                    int x = cell.getColumnIndex() - xDiff;
                    if (!inRange) {
                        short borderLeft = cell.getCellStyle().getBorderLeft();
                        short borderTop = cell.getCellStyle().getBorderTop();

                        if (borderLeft + borderTop == 4) {
                            yDiff = cell.getRowIndex();
                            xDiff = cell.getColumnIndex();
                            matrix = new int[lastRowNum - yDiff + 1][lastCellNum - xDiff];
                            inRange = true;
                            if ("s".equals(cell.getStringCellValue())) {
                                y = cell.getRowIndex() - yDiff;
                                x = cell.getColumnIndex() - xDiff;
                                matrix[y][x] = -2;
                            }
                        }
                    } else {
                        if (cell.getCellStyle().getFillPattern() == 1) {
                            matrix[y][x] = -1;
                        }
                        if ("s".equals(cell.getStringCellValue())) {
                            matrix[y][x] = -2;
                        }
                        if ("f".equals(cell.getStringCellValue())) {
                            matrix[y][x] = -3;
                        }
                    }
                }
            }
        }
        return matrix;
    }
}
