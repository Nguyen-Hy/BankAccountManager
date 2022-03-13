package com.company;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static boolean isValidPhone(String s)
    {

        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // The number should be of 10 digits.

        // Creating a Pattern class object
        Pattern p = Pattern.compile("^\\d{10}$");

        var startWith = s.startsWith("0");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression for which
        // object of Matcher class is created
        Matcher m = p.matcher(s);

        // Returning boolean value
        if( startWith ){
            return (m.matches());
        }else {
            return false;
        }
    }

    public static void main(String[] args) throws ParseException, FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        ArrayList<ABC_BANK> abcBanks = new ArrayList<>();
        CheckDob checkDob = new CheckDob();
        Sever abc_bank = new ABC_BANK();

        Sever sever = new Sever() {
            @Override
            String getBankName() {
                return null;
            }
        };

        var fileName = "E:\\FILE SAVE\\java\\BankAccount_manager\\src\\com\\company\\abc_data.txt";
        readFile(fileName);
        abcBanks.addAll(readFile(fileName));
//        abc_bank.checkMember();
        var choose = 0;
        do {

            System.out.println("\n");
            System.out.println("\n\t ======================= MENU ===================================");
            System.out.println("\t||  1.  THÊM MỚI TÀI KHOẢN                                      ||");
            System.out.println("\t||  2.  HIỂN THỊ THÔNG TIN TÀI KHOẢN                            ||");
            System.out.println("\t||  3.  NẠP TIỀN VÀO TÀI KHOẢN                                  ||");
            System.out.println("\t||  4.  RÚT TIỀN RA KHỎI TÀI KHOẢN                              ||");
            System.out.println("\t||  5.  CHUYỂN TIỀN                                             ||");
            System.out.println("\t||  6.  TÌM TÀI KHOẢN THEO (Tên, Số tài khoản, số tiền (>= X)   ||");
            System.out.println("\t||  7.  XÓA MỘT TÀI KHOẢN (theo mã)                             ||");
//            System.out.println("\t||  8.  GHI DANH SÁCH TÀI KHOẢN RA FIE                          ||");
            System.out.println("\t||  0.  THOÁT CHƯƠNG TRÌNH                                      ||");
            System.out.println("\t ================================================================");
            System.out.print("\t =====> XIN MỜI CHỌN   ");
            choose = scanner.nextInt();

            switch (choose) {

                case 1: {
                    var acB = createNewAccount(sever);
                    abcBanks.add(acB);
                    writeFile(abcBanks, fileName);
                    System.out.println("Them tai khoan vao danh sach thanh cong. ");
                    break;
                }

                case 2: {
                    if (abcBanks.size() > 0) {
                        showData_ABC(abcBanks);
                    } else {
                        System.out.println("Danh sach tai khoan rong! ");
                    }
                    break;
                }

                case 3: {
                    if( abcBanks.size() > 0 ){
                        addMoney(abcBanks);
                        writeFile(abcBanks, fileName);
                    }else{
                        System.out.println("Danh sach tai khoan rong! ");
                    }
                    break;
                }

                case 4: {
                    if( abcBanks.size() > 0 ){
                        minusMoney(abcBanks);
                        writeFile(abcBanks, fileName);
                    }else{
                        System.out.println("Danh sách tài khoản rỗng! ");
                    }
                    break;
                }

                case 5: {
                    if( abcBanks.size() >= 2 ){
                        transfers( abcBanks);
                    }
                    break;
                }

                case 6: {
                    if( abcBanks.size() > 0 ){
                        var chosse2 = 0;
                        do {
                            System.out.println("\n\t========================================");
                            System.out.println("\t|| 1. Tìm tài khoản theo tên          ||");
                            System.out.println("\t|| 2. Tìm tài khoản theo số tài khoản ||");
                            System.out.println("\t|| 3. Tìm tài khoản theo số dư >=x    ||");
                            System.out.println("\t|| 0. Thoát chương trình tìm kiếm     ||");
                            System.out.println("\t========================================");
                            System.out.print("\tNhập lựa chọn: ");
                            chosse2 = scanner.nextInt();

                            switch (chosse2 ){
                                case 1:{
                                    srcFindEmpName(abcBanks);
                                    break;
                                }
                                case 2:{
                                    findAccountByNumber(abcBanks);
                                    break;
                                }
                                case 3:{
//                                    var money = scanner.nextDouble();
                                    _findSurplus(abcBanks);
                                    break;
                                }
                                case 0: {
                                    break;
                                }
                                default:{
                                    System.out.println("\n\t Lựa chọn không hợp lệ. Vui lòng nhập lại: ");
                                }
                            }
                        }while (chosse2 != 0 );
                    }else{
                        System.out.println("Danh sách tài khoản rỗng! ");
                    }

                    break;
                }

                case 7: {
                    if( abcBanks.size() > 0 ){
                        var rm = removeAc( abcBanks );
                        if( rm != null ){
                            System.out.println("\tXóa tài khoản thành công");
                            abcBanks.remove(rm);
                            writeFile(abcBanks, fileName);
                        }
                    }else{
                        System.out.println("Danh sach tai khoan rong! ");
                    }
                    break;
                }

                case 0: {
                    System.out.println("Thoát Chương Trình. ");
                    break;
                }
                default:{
                    System.out.println("\n Không hợp lê, vui lòng nhập lại!");
                }

            }

        } while (choose != 0);

    }

    private static ABC_BANK removeAc(ArrayList<ABC_BANK> abcBanks) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tNhập số tài khoản cần xóa: " );
        var acNum = "";
        var check = new ABC_BANK();
        do{
            acNum = scanner.next();
            check = findAcByNum(acNum, abcBanks);
            if(check == null ){
                System.out.println("\tKhông tìm thấy tài khoản. Xóa tài khoản thất bại");
            }
            return check;
        }while (check == null );

    }


    private static void transfers( ArrayList<ABC_BANK> abc_banks) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tNhập số tài khoản người chuyển: ");
        var acSent = "";
        var checkSent = new ABC_BANK();
        var checkMoney = true;
        do{
            acSent = scanner.next();
            checkSent = findAcByNum(acSent, abc_banks);
            if( checkSent == null ){
                System.out.println("\tKhông tìm thấy tài khoản có tên \" " + acSent + " \" ");
                System.out.print("\tNhập lại: ");
            }
        }while (checkSent == null);

        System.out.print("\n\tNhập số tài khoản người nhận: ");
        var acRecive = "";
        var checkRe = new ABC_BANK();
        var checkRe2 = new ABC_BANK();
        do{
            acRecive = scanner.next();
            checkRe = findAcByNumByRevice(acRecive, acSent, abc_banks);
//            checkRe2 = findAcByNum(acRecive, abc_banks);
            if( checkRe == null ){
                System.out.println("\tKhông tìm thấy tài khoản có tên \" " + acRecive + " \" ");
                System.out.print("\tNhập lại: ");
            }
        }while (checkRe == null);

        System.out.print("\n\tNhập số tiền: ");
        var money = 0.0f;
        do{
            money = scanner.nextFloat();
            if( money < 49.0 ){
                System.out.print("\n\t Số tiền chuyển phải (>= 50 <= 99999999.9)" );
                System.out.print("\tNhập lại: ");
            }
        }while (money < 49.0 && money > 99999999.9);

       System.out.print("\n\t Nội dung giao dịch: ");
        var mess = "";
        do{
            mess = scanner.nextLine();
            if( mess == " " ){
                System.out.println("\tKhông được để trống nội dung giao dịch.");
                System.out.print("\tNhập lại: ");
            }
        }while (mess == " ");
        Date date = new Date();
        checkSent.addMoney(money);
        checkRe.minusMoney(money);
        showAcInforTransfers(money, date, acSent, acRecive, checkSent.getAcountName(), checkRe.getAcountName(), mess );
    }

    private static void showAcInforTransfers( double money, Date date, String sentId, String reciveId, String sendName, String reciveName, String text){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - k:m:s:a");
        System.out.println("\n\n\t\tGiao Dịch Thành Công");
        System.out.println("===================================");
        System.out.println("\t\tSố tiền chuyển khoản");
        System.out.printf("\t\t" + money + "\n");
        System.out.println("===================================");
        System.out.println("\tNgày giao dịch");
        System.out.println( "\t"+ dateFormat.format(date));
        System.out.println("-----------------------------------");
        System.out.println("\tNgười gủi");
        System.out.println("\t" + sendName + "\n\t" + sentId);
        System.out.println("-----------------------------------");
        System.out.println("\tNgười nhận");
        System.out.println("\t" + reciveName + "\n\t" + reciveId);
        System.out.println("-----------------------------------");

        System.out.println("\tNội dung giao dịch");
        System.out.println("\t"  + text);

        System.out.println("-----------------------------------");
        System.out.println("\tMã giao dịch");
    }

//    private static void findSurplus( ArrayList<ABC_BANK> abc_banks ){
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("\n\tNhap so so du: ");
//        var surplus = scanner.nextDouble();
//        var checkSurplus = _findSurplus(abc_banks, surplus);
//        if( checkSurplus != null ){
//            System.out.println("\n\tTim thay tk co so du >=" + surplus);
//            System.out.println("\n\n");
//            for( var i : abc_banks){
//                showEqualsDown();
//                System.out.printf("\t %-10s %-20s %-20s %-20s %-15s %-25s %-20s %-20s %-25s %-25s ",
//                        "MÃ TK", "TÊN TK", "SỐ TK", "SỐ DƯ", "LOẠI TK", "NGÂN HÀNG PHÁT HÀNH", "SỐ ĐT", "ĐỊA CHỈ", "CẤP THÀNH VIÊN", "NGÀY PHÁT HÀNH");
//                acData_ABC(i);
//                showEqualsDown();
//            }
//
//        }else{
//            System.out.println("\n\t khong tim thay tai khoan co so du >= " + surplus);
//        }
//    }

    private static void  _findSurplus( ArrayList<ABC_BANK> bankInforArrayList ){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\n\tNhập số tiền: ");
        var money = scanner.nextFloat();
        for( var i : bankInforArrayList ){
            if( i.getSurplus() >= money  ){
//                System.out.println("\n\n");
                showEqualsDown();
                System.out.printf("\t %-10s %-20s %-20s %-20s %-15s %-25s %-20s %-20s %-25s %-25s ",
                        "MÃ TK", "TÊN TK", "SỐ TK", "SỐ DƯ", "LOẠI TK", "NGÂN HÀNG PHÁT HÀNH", "SỐ ĐT", "ĐỊA CHỈ", "CẤP THÀNH VIÊN", "NGÀY PHÁT HÀNH");
                acData_ABC(i);
                showEqualsDown();
            }else{
                System.out.println("\tKhông tìm tháy tài khoản có số dư >= (" + money + ")");
                break;
            }
        }

    }

//    private static ABC_BANK _findSurplus( ArrayList<ABC_BANK> abc_banks, double surplus ){
//        for( var i : abc_banks ){
//            if( i.getSurplus() >= surplus  ){
//                return i;
//            }
//        }
//        return null;
//    }

//    private static void findAcByName(ArrayList<ABC_BANK> abc_banks) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("\n\t Nhập tên tài khoản cần tìm: ");
//        var empName = scanner.nextLine();
//        var names = empName.split(" ");
//        var lastName = names[names.length-1];
//        var src = srcFindEmpName(lastName, abc_banks);
//        System.out.println("\n\n");
//        System.out.println("\tTìm thấy tài khoản có tên \"" + empName + "\"");
//
//        if( src != null ){
//                showEqualsDown();
//                System.out.printf("\t %-10s %-20s %-20s %-20s %-15s %-25s %-20s %-20s %-25s %-25s ",
//                        "MÃ TK", "TÊN TK", "SỐ TK", "SỐ DƯ", "LOẠI TK", "NGÂN HÀNG PHÁT HÀNH", "SỐ ĐT", "ĐỊA CHỈ", "CẤP THÀNH VIÊN", "NGÀY PHÁT HÀNH");
//                acData_ABC(src);
//                showEqualsDown();
//        }else {
//            System.out.println("\tKhông tìm thấy tài khoản có tên \" " + lastName + " \" ");
//        }
//
//    }

    private static void srcFindEmpName ( ArrayList<ABC_BANK> abc_banks){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\t Nhập tên tài khoản cần tìm: ");
                var acName = scanner.nextLine();
                var names = acName.split(" ");
                var lastName = names[names.length-1];

        for( var i : abc_banks ){
            var namess = findLastName(i.getAcountName());
            if( namess.equalsIgnoreCase(lastName)){
                                showEqualsDown();
                System.out.printf("\t %-10s %-20s %-20s %-20s %-15s %-25s %-20s %-20s %-25s %-25s ",
                        "MÃ TK", "TÊN TK", "SỐ TK", "SỐ DƯ", "LOẠI TK", "NGÂN HÀNG PHÁT HÀNH", "SỐ ĐT", "ĐỊA CHỈ", "CẤP THÀNH VIÊN", "NGÀY PHÁT HÀNH");
                acData_ABC(i);
                showEqualsDown();
            }else{
                System.out.println("\tKhông tìm thấy tài khoản có tên \" " + lastName + " \" ");
                break;
            }
        }

    }


//private static ABC_BANK srcFindEmpName ( String name, ArrayList<ABC_BANK> employeeArrayList){
//    for( var i : employeeArrayList ){
//        var names = findLastName(i.getAcountName());
//        if( names.equalsIgnoreCase(name)){
//            return i;
//        }
//    }
//    return null;
//}


    private static String findLastName(String name ){
        var lastName = name.lastIndexOf(" ");
        return name.substring(lastName+1);
    }


    private static boolean checkMoney( String id, ArrayList<ABC_BANK> abc_banks){
        for( var i : abc_banks ){
            if( i.getAcountNumber().equals(id)){
                if( i.getSurplus() >= 55.00);
                return true;
            }
        }
        return false;
    }



    private static void minusMoney(ArrayList<ABC_BANK> abcBanks) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tNhập số tài khoản cần rút: ");
        var acNum = scanner.nextLine();
        var check = findAcByNum(acNum, abcBanks);

            if( check != null ){
                System.out.print("\n\tNhập số tiền cần rút ( >= 50.000 ): ");
                var money = scanner.nextDouble();
                do{
                    if( money > 50.000 ) {
                        check.minusMoney(money);
                        System.out.print("\n\tĐã rút thành công (" + money + ") vào số tài khoản ( " + money + ")" );
                    }else{
                        System.out.print("\n\tSố tiền rút phải > 50.000. Vui lòng nhập lại số tiền");
                        money = scanner.nextDouble();
                    }
                }while (money < 50.000);

            }else{
                System.out.println("\tKhông tìm thấy tài khoản có số (" + acNum + ").");
            }

    }

    private static ABC_BANK findAcByNum(String acNum, ArrayList<ABC_BANK> abc_banks){
        for( var i : abc_banks ){
            if( i.getAcountNumber().equals(acNum)){
                return i;
            }
        }
        return null;
    }

    private static ABC_BANK findAcByNumByRevice(String acNumRecive, String acNumSent, ArrayList<ABC_BANK> abc_banks){
        for( var i : abc_banks ){
            if( ! i.getAcountNumber().equals(acNumSent) && i.getAcountNumber().equals(acNumRecive)){
                return i;
            }
        }
        return null;
    }

    private static void addMoney(ArrayList<ABC_BANK> abcBanks) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tNhập số tài khoản cần nạp: ");
        var acNum = scanner.nextLine();

            var check = findAcByNum(acNum, abcBanks);
            if( check != null ){

                System.out.print("\n\ttNhập số tiền cần nạp ( >= 50.000 ): ");
                var money = scanner.nextDouble();
                do{
                    if( money > 50 ) {
                        check.addMoney(money);
                        System.out.print("\n\tĐã nạp thành công (" + money + ") vào số tài khoản ( " + money + ")" );
                    }else{
                        System.out.print("\n\tSố tiền nạp phải > 50.000. Vui lòng nhập lại số tiền");
                        money = scanner.nextDouble();
                        check.addMoney(money);
                    }
                }while (money < 50);

            }else{
                System.out.println("\tKhông tìm thấy tài khoản có số (" + acNum + ").");
            }
    }

    private static void findAccountByNumber( ArrayList<ABC_BANK> abc_banks) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tNhập số tài khoản cần tìm: ");
        var acNum = scanner.nextLine();
        var check = findAcByNum(acNum, abc_banks);
            if( check != null ){
                System.out.println("\n\n");
                System.out.println("\tTìm thấy tài khoản có số (" + acNum + ").");
                showEqualsDown();
                System.out.printf("\t %-10s %-20s %-20s %-20s %-15s %-25s %-20s %-20s %-25s %-25s ",
                        "MÃ TK", "TÊN TK", "SỐ TK", "SỐ DƯ", "LOẠI TK", "NGÂN HÀNG PHÁT HÀNH", "SỐ ĐT", "ĐỊA CHỈ", "CẤP THÀNH VIÊN", "NGÀY PHÁT HÀNH");
                acData_ABC(check);
                showEqualsDown();
            }else{
                System.out.println("\tKhông tìm thấy tài khoản có số (" + acNum + ").");
            }

    }

    private static ABC_BANK createNewAccount( Sever sv ) throws ParseException {

        SimpleDateFormat dateFormatTime = new SimpleDateFormat("dd/MM/yyyy - k:m:s:a");
        SimpleDateFormat dateFormatDob = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<ABC_BANK> abc_banks = new ArrayList<>();
        ABC_BANK abc_bank = new ABC_BANK();
//        Date date = new Date();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tNhap ten tk: ");
        var acName = scanner.nextLine();

        System.out.print("\n\tNhap so tk (8 chu so): ");
        String acNumber = "";
        do{
            acNumber = scanner.next();
            if( acNumber.length() > 8 || acNumber.length() < 8 ){
                System.out.print("\t Số tài khoản phải đú 8 chữ số. Nhập lại: ");
            }
        }while ( acNumber.length() > 8 || acNumber.length() < 8 );

        scanner.nextLine();
        System.out.print("\n\tNhap dia chi: ");
        var addr = scanner.nextLine();

        System.out.print("\n\tNhap so dien thoai: ");
        var phoneNum = "";
        do {
            phoneNum = scanner.nextLine();
            if( isValidPhone(phoneNum) != true ){
                System.out.print("\n\tSo dien thoai khong hop le. Vui long nhap lai!: ");
            }
        }while (isValidPhone(phoneNum) != true );

        System.out.println(phoneNum);

        System.out.print("\n\tNhap loai tk (tt, tk, vv, qt): ");
        var typeAc = "";
        String[] parten = {"tt", "tk", "td", "vv"};
        do{
            typeAc = scanner.nextLine();
            if(!typeAc.equalsIgnoreCase(parten[0]) && !typeAc.equalsIgnoreCase(parten[1]) && !typeAc.equalsIgnoreCase(parten[2]) && !typeAc.equalsIgnoreCase(parten[3]) ){
                System.out.print("\n\tKhông hợp lệ. Nhập lại: ");
            }
        }while ( !typeAc.equalsIgnoreCase(parten[0]) && !typeAc.equalsIgnoreCase(parten[1]) && !typeAc.equalsIgnoreCase(parten[2]) && !typeAc.equalsIgnoreCase(parten[3])  );

        System.out.print("\n\tNhập ngày sanh (mm-dd-yyyy): ");
        String dob = "";
        do {
            dob = scanner.nextLine();
            if( CheckDob.isValidDate(dob) != true){
                System.out.print("\n\tNgày sanh không hợp lệ. Nhập lại ");
            }

        }while (CheckDob.isValidDate(dob) != true );
        var doB = dateFormatDob.parse(dob);
//        System.out.print("Nhap loai tien (VND, USD, AUD,...):  ");
//        var typeMoney = scanner.nextLine();
        var chosse = 0;
        System.out.print("\n\t(1. Nhập ngày phát hành) || (2. Ngày hiện tại)");
        chosse = scanner.nextInt();
        scanner.nextLine();
        Date startDay = null;

        switch (chosse ){
            case 1:{

                var check = "";
                do{
                    System.out.print("\n\tNhập ngày phát hành: ");
                     check = scanner.nextLine();
                     if(CheckDob.isValidDate(check) != true){
                         System.out.print("\n\tNgày phát hành không hợp lệ. Nhập lại");
                     }
                }while (CheckDob.isValidDate(check) != true);
                startDay = dateFormatTime.parse(check);
                break;
            }
            case 2:{
                startDay = new Date();
                break;
            }
        }

        return new ABC_BANK( null, acName, acNumber, typeAc, doB, addr, phoneNum, 0.0f,
                null, null, startDay  );
    }


    private static void acData_ABC( ABC_BANK abc_bank){
        SimpleDateFormat dateFormatTime = new SimpleDateFormat("MM/dd/yyyy - k:m:s:a");
        SimpleDateFormat dateFormatDob = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("\n\t%-10s %-20s %-20s %-20s %-15s %-25s %-20s %-20s %-25s %-25s  ",
                abc_bank.getId(), abc_bank.getAcountName().toUpperCase(Locale.ROOT), abc_bank.getAcountNumber(), abc_bank.getSurplus(), abc_bank.getAcountType(),
                abc_bank.getBankName() , abc_bank.getPhoneNumber(), abc_bank.getAddr(), abc_bank.calVlMember(), dateFormatTime.format(abc_bank.getStartDate()));
    }

    private static void showData_ABC( ArrayList<ABC_BANK> abc_banks ){
//        SimpleDateFormat dateFormat = new SimpleDateForm2at("dd/mmm/yyyy");
        System.out.println("\n\n");
        showEqualsDown();
        System.out.printf("\t %-10s %-20s %-20s %-20s %-15s %-25s %-20s %-20s %-25s %-25s ",
                             "MÃ TK", "TÊN TK", "SỐ TK", "SỐ DƯ", "LOẠI TK", "NGÂN HÀNG PHÁT HÀNH", "SỐ ĐT", "ĐỊA CHỈ", "CẤP THÀNH VIÊN", "NGÀY PHÁT HÀNH");
        for( var i : abc_banks){
            acData_ABC(i);
        }
        showEqualsDown();
    }

    private static boolean writeFile( ArrayList<ABC_BANK> abc_banks , String fileName ){
        try {
            Scanner scanner = new Scanner( new File(fileName) );
            ABC_BANK abc_bank = new ABC_BANK();
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("MM/dd/yyyy - k:m:s:a");
            SimpleDateFormat dateFormatDob = new SimpleDateFormat("dd/MM/yyyy");

            for( int i = 0; i < abc_banks.size(); i++ ){
                var emp = abc_banks.get(i);
                printWriter.println(emp.getId());
                printWriter.println(emp.getAcountName().toUpperCase(Locale.ROOT));
                printWriter.println(emp.getAcountNumber());
                printWriter.println(emp.getAcountType());
                printWriter.println(dateFormatDob.format(emp.getDob()));
//                printWriter.println(dateFormatDob.format(abc_bank.getDob()));
                printWriter.println(emp.getAddr());
                printWriter.println(emp.getPhoneNumber());
                printWriter.println(emp.getSurplus());
                printWriter.println(emp.getBankName());
                printWriter.println(emp.calVlMember());
                printWriter.println(simpleDateFormatTime.format(emp.getStartDate()));
//                printWriter.println(simpleDateFormat.format(emp.getEndDate()));

            }

            printWriter.close();
            fileWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static ArrayList<ABC_BANK> readFile(String fileName ) throws FileNotFoundException, ParseException {

        ArrayList<ABC_BANK> abc_banks= new ArrayList<>();

        var file = new File(fileName);
        var scanner = new Scanner(file);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy - k:m:s:a");
        SimpleDateFormat dateFormatDob = new SimpleDateFormat("dd/MM/yyyy");

        while( scanner.hasNextLine() ){
            String id = scanner.nextLine();
            var name = scanner.nextLine();
            String acountNumber = scanner.nextLine();
            var type = scanner.nextLine();
            var dob = dateFormatDob.parse(scanner.nextLine());
            var addr = scanner.nextLine();
            var phoneNumber = scanner.nextLine();
            var surplus = Double.parseDouble(scanner.nextLine());
            var bankname = scanner.nextLine();
            var lvMem = scanner.nextLine();
            var startDate = simpleDateFormat.parse(scanner.nextLine());

            ABC_BANK bankInfor = new ABC_BANK(id, name, acountNumber, type, dob, addr, phoneNumber, surplus, bankname, lvMem, startDate );
            abc_banks.add(bankInfor);
        }
        scanner.close();
        return abc_banks;
    }


    private static void showEqualsUp(){
        System.out.println("\n==============================================================================================================================================================================================================");
    }

    private static void showEqualsDown(){
        System.out.println("===========================================================================================================================================================================================================================");
    }


}
