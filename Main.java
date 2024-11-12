// Bilgi Islem Menusu Uygulamasi


import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        // Kullanıcıdan isim ve soyisim alma
        String name = JOptionPane.showInputDialog(null, "Lütfen isminizi girin:");
        String surname = JOptionPane.showInputDialog(null, "Lütfen soyisminizi girin:");

        // İşlem yaparken döngü kullanacağız
        boolean exit = false;

        while (!exit) {
            // Menü seçeneklerini göster
            String menu = "Menü:\n"
                    + "1. Uppercase/Lowercase Çevirme\n"
                    + "2. Boşlukları Kaldırma\n"
                    + "3. İstediğiniz Harfi Değiştirme\n"
                    + "4. Çıkış\n"
                    + "5. Bilgilerimi Göster\n"
                    + "Bir seçenek girin (1-5):";

            // Kullanıcıdan seçenek alma
            String choice = JOptionPane.showInputDialog(null, menu);

            switch (choice) {
                case "1":
                    // Büyük/Küçük harfe çevirme
                    String subMenu = "1. Büyük Harfe Çevir\n"
                            + "2. Küçük Harfe Çevir\n"
                            + "Bir seçenek girin (1-2):";
                    String caseChoice = JOptionPane.showInputDialog(null, subMenu);
                    if (caseChoice.equals("1")) {
                        name = name.toUpperCase();
                        surname = surname.toUpperCase();
                        JOptionPane.showMessageDialog(null, "İsim ve Soyisim büyük harfe çevrildi:\n" + name + " " + surname);
                    } else if (caseChoice.equals("2")) {
                        name = name.toLowerCase();
                        surname = surname.toLowerCase();
                        JOptionPane.showMessageDialog(null, "İsim ve Soyisim küçük harfe çevrildi:\n" + name + " " + surname);
                    } else {
                        JOptionPane.showMessageDialog(null, "Geçersiz seçim!");
                    }
                    break;

                case "2":
                    // Boşlukları kaldırma
                    name = name.replaceAll(" ", "");
                    surname = surname.replaceAll(" ", "");
                    JOptionPane.showMessageDialog(null, "Boşluklar kaldırıldı:\n" + name + " " + surname);
                    break;

                case "3":
                    // İstediğimiz harfi değiştirme
                    String oldChar = JOptionPane.showInputDialog(null, "Değiştirmek istediğiniz harfi girin:");
                    String newChar = JOptionPane.showInputDialog(null, "Yerine koymak istediğiniz harfi girin:");
                    name = name.replace(oldChar.charAt(0), newChar.charAt(0));
                    surname = surname.replace(oldChar.charAt(0), newChar.charAt(0));
                    JOptionPane.showMessageDialog(null, "Harf değiştirildi:\n" + name + " " + surname);
                    break;

                case "4":
                    // Programdan çıkış
                    exit = true;
                    JOptionPane.showMessageDialog(null, "Çıkılıyor...");
                    break;

                case "5":
                    // Bilgileri gösterme
                    JOptionPane.showMessageDialog(null, "İsim: " + name + "\nSoyisim: " + surname);
                    break;

                default:
                    // Geçersiz seçim
                    JOptionPane.showMessageDialog(null, "Geçersiz seçim! Lütfen 1-5 arası bir sayı girin.");
            }
        }
    }
}