package restassure.reqres.utils;

import io.qameta.allure.Attachment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Capturer {
    @Attachment(fileExtension = "png")
    public static byte[] takeScreenshot() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Robot robot = new Robot();
            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage image = robot.createScreenCapture(capture);
            ImageIO.write(image, "png", out);
//            Allure.addAttachment(LocalDate.now() + ".png", new ByteArrayInputStream(out.toByteArray()));
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
}
