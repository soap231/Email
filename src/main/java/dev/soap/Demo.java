package dev.soap;

public class Demo {
    public static void main(String[] args) {
        // environment variables (email,password) needed
        String text = "Hello!!!";
        EmailUtil.sendEmailWithText("revature.team.3@gmail.com", "testTEXT", text);

        String html = "";
        //For online image, right click the image -> click "copy image address"
        String imgURL = "https://cdn.images.express.co.uk/img/dynamic/143/590x/Genshin-Impact-1-1-1357677.webp?r=1604845157446";
        html += "<br><img src=' " + imgURL + " 'alt='img' width='100'>";
        EmailUtil.sendEmailWithHtml("revature.team.3@gmail.com", "testHTML", html);
    }
}
