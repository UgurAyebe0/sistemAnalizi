package ugurayebe.fun.controller.frame;

import ugurayebe.fun.listener.showMessage;

import java.util.ArrayList;

import static ugurayebe.fun.database.config.jdbcTemplate;

public class validData {
    public static boolean valid(ArrayList fieldData, ArrayList fieldOrder, String frameType, Object id, Boolean update) {

        for (int i = 0; i < fieldData.size(); i++) {

            String label = (String) fieldData.get(i);
            String field = (String) fieldOrder.get(i);
            System.out.println(frameType + " " + field);

            if (label.isEmpty() && !field.startsWith("jC")) {
                showMessage.main(field + " cannot be left blank.");
                return false;
            }

            if (label.length() > 35) {
                showMessage.main(field + " It can be up to 35 characters.");
                return false;
            }


            if (update) {
                switch (frameType) {
                    case "Title":
                        if (countRecords2(frameType, field, label, id)) {
                            showMessage.main(field + "(" + label + ") lütfen benzersiz birşey yapın.");
                            return false;
                        }
                    case "Teacher":
                        if (field.equals("TC") || field.equals("PhoneNumber")) {
                            if (label.length() != 11) {
                                showMessage.main(field + " lütfen 11 rakamlı girin. (" + label.length() + ")");
                                return false;
                            } else if (isDuplicated(field, frameType, label, id)) {
                                showMessage.main(field + " zaten kullanılıyor.");
                                return false;
                            } else if (!containsNumericalDigit(label)) {
                                showMessage.main(field + " bilgisine lütfen sadece rakam girin.");
                                return false;
                            }
                        }
                        if (field.equals("Username")) {
                            if (isDuplicated(field, frameType, label, id)) {
                                showMessage.main(field + " zaten kullanılıyor.");
                                return false;
                            }
                        }
                        break;
                }
            } else {
                switch (frameType) {
                    case "Title":
                        if (countRecords(frameType, field, label)) {
                            showMessage.main(field + " zaten kullanılıyor.");
                            return false;
                        }
                        break;
                    case "Teacher":
                        if (field.equals("TC") || field.equals("PhoneNumber")) {
                            if (label.length() != 11) {
                                showMessage.main(field + " lütfen 11 rakamlı girin. (" + label.length() + ")");
                                return false;
                            } else if (countRecords(frameType, field, label)) {
                                showMessage.main(field + " zaten kullanılıyor.");
                                return false;
                            } else if (!containsNumericalDigit(label)) {
                                showMessage.main(field + " bilgisine lütfen sadece rakam girin.");
                                return false;
                            }
                        }
                        if (field.equals("Username")) {
                            if (countRecords(frameType, field, label)) {
                                showMessage.main(field + " zaten kullanılıyor.");
                                return false;
                            }
                        }
                        break;
                }
            }





//
//            String control = process + field;
//            System.out.println(control);
//            if (update) {
//                switch (control) {
//                    case "TeachersTC":
//                    case "TeachersPhoneNumber":
//                        if (label.length() != 11) {
//                            showMessage("The " + field + " must be 11 letters long. (" + label.length() + " )");
//                            return false;
//                        } else if (isDuplicated(field, process, label, İd)) {
//                            showMessage("This " + field + " is already in use.");
//                            return false;
//                        } else if (!containsNumericalDigit(label)) {
//                            showMessage(field + " Please enter only numbers.");
//                            return false;
//                        }
//                        break;
//                    case "teacher_availabilityUsername", "ClassroomsName", "TeachersUsername", "TitlesTitle":
//                        if (isDuplicated(field, process, label, İd)) {
//                            showMessage("This " + field + " is already in use.");
//                            return false;
//                        }
//                        break;
//                }
//            } else {
//                switch (control) {
//                    case "TeachersTC", "TeachersPhoneNumber":
//                        if (label.length() != 11) {
//                            showMessage("The " + field + " must be 11 letters long. (" + label.length() + " )");
//                            return false;
//                        } else if (isDuplicated(field, process, label)) {
//                            showMessage("This " + field + " is already in use.");
//                            return false;
//                        }
//                        break;
//                    case "teacher_availabilityUsername", "ClassroomsName", "TitlesTitle":
//                        if (isDuplicated(field, process, label)) {
//                            showMessage("This " + field + " is already in use.");
//                            return false;
//                        }
//                        break;
//                }
//            }


//            if (process.equals("teacher_courses")) {
//                String sqlTeachers = "SELECT * FROM teachers WHERE username = ?";
//                String resulData = returnİd(sqlTeachers, (String) fieldData.get(i), "İd");
//                String sqlLesson = "SELECT * FROM lessons WHERE Name = ?";
//                String resulData2 = returnİd(sqlLesson, (String) fieldData.get(i + 1), "İd");
//                String sql = "SELECT COUNT(*) FROM teacher_courses where Teachers = " + resulData + " And lesson = " + resulData2; //  855
//                int ClassromAvailables = getRowCount(sql);
//                if (ClassromAvailables == 0) {
//                    return true;
//                } else {
//                    showMessage("The user " + fieldData.get(i) + " has already been assigned a " + fieldData.get(i + 1) + " course.");
//                    return false;
//                }
//            }
//

        }


        return true;
    }


    private static boolean isDuplicated(String field, String process, String label, Object id) {
        String sql = "SELECT COUNT(*) FROM " + process + " WHERE " + field + " = ? AND id != ?";
        return whereBoolean(sql, new Object[]{label, id});
    }

    public static boolean countRecords(String frameType, String field, String label) {
        String sql = "SELECT COUNT(*) FROM " + frameType + " WHERE " + field + " = ?";
        Object[] params = {label};
        return 0 != jdbcTemplate.queryForObject(sql, Integer.class, params);
    }

    public static boolean countRecords2(String frameType, String field, String label, Object id) {
        String sql = "SELECT COUNT(*) FROM " + frameType + " WHERE " + field + " = ? AND id != ?";
        Object[] params = {label, id};
        return 0 != jdbcTemplate.queryForObject(sql, Integer.class, params);
    }


    private static boolean whereBoolean(String sql, Object[] params) {
        return jdbcTemplate.queryForObject(sql, Integer.class, params) > 0;
    }

    private static boolean containsNumericalDigit(String input) {
        return input.matches(".*\\d.*");
    }

}
