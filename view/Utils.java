package view;

import model.User;
import model.dto.UserResponseDTO;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class Utils {

    private final Scanner sc =
            new Scanner(System.in);

    public int menu() {

        System.out.print("""
                ==========================
                   ប្រព័ន្ធគ្រប់គ្រង់ទិន្នន័យអ្នកប្រើប្រាស់
                ==========================
                1. បញ្ជីអ្នកប្រើប្រាស់ 
                2. ចុះឈ្មោះអ្នកប្រើប្រាស់
                3. ស្វែងរកអ្នកប្រើប្រាស់
                4. ធ្វើបច្ចុប្បន្នភាពអ្នកប្រើប្រាស់
                5. លុបទិន្នន័យអ្នកប្រើប្រាស់
                0. ចាកចេញពីប្រព័ន្ធ
                ==========================
                """);

        System.out.print("បញ្ចូលជម្រើស: ");

        return Integer.parseInt(sc.nextLine());
    }

    public String input(String label) {

        System.out.print(label + ": ");

        return sc.nextLine();
    }

    public static void userList(List<APIResponseTemplate<List<UserResponseDTO>>> userList) {
        Table table = new Table(8, BorderStyle.UNICODE_ROUND_BOX_WIDE);

        CellStyle center =
                new CellStyle(CellStyle.HorizontalAlign.center);

        final String BOLD = "\033[1m";
        final String RED = "\033[31m";
        final String RESET = "\033[0m";

        table.addCell(BOLD + RED + "ID" + RESET, center);
        table.addCell(BOLD + RED + "UUID" + RESET, center);
        table.addCell(BOLD + RED + "NAME" + RESET, center);
        table.addCell(BOLD + RED + "EMAIL" + RESET, center);
        table.addCell(BOLD + RED + "PROFILE" + RESET, center);
        table.addCell(BOLD + RED + "STATUS" + RESET, center);
        table.addCell(BOLD + RED + "MESSAGE" + RESET, center);
        table.addCell(BOLD + RED + "TIMESTAMP" + RESET, center);

        for ( APIResponseTemplate<List <UserResponseDTO>> responseTemplate : userList) {
            for (UserResponseDTO user: responseTemplate.data()){
                table.addCell(user.id().toString());
                table.addCell(user.uuid().toString());
                table.addCell(user.name());
                table.addCell(user.email());
                table.addCell(user.profile());

                table.addCell(String.valueOf(responseTemplate.status()));
                table.addCell(String.valueOf(responseTemplate.message()));
                table.addCell(String.valueOf(responseTemplate.timeStamp()));
            }

        }

        System.out.println(table.render());
    }

    public static void printUserInfo(UserResponseDTO user) {


        Table table = new Table(2, BorderStyle.UNICODE_ROUND_BOX_WIDE);

        CellStyle center =
                new CellStyle(CellStyle.HorizontalAlign.center);

        final String BOLD = "\033[1m";
        final String BLUE = "\033[34m";
        final String RESET = "\033[0m";

        table.addCell(BOLD + BLUE + "FIELD" + RESET, center);
        table.addCell(BOLD + BLUE + "VALUE" + RESET, center);

        table.addCell("ID");
        table.addCell(user.id().toString());

        table.addCell("UUID");
        table.addCell(user.uuid().toString());

        table.addCell("NAME");
        table.addCell(user.name());

        table.addCell("EMAIL");
        table.addCell(user.email());

        table.addCell("PROFILE");
        table.addCell(user.profile());

        System.out.println(table.render());
    }
}
