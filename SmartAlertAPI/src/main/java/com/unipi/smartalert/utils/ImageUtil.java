package com.unipi.smartalert.utils;

import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@UtilityClass
public class ImageUtil {

    public static void saveImageToDisk(byte[] byteArray, String path) {
        try {
            BufferedImage image = convertByteArrayToJPEG(byteArray);
            ImageIO.write(image, "jpeg", new File(path));
        } catch (IOException e) {
            // TODO: throw a custom exception
            throw new RuntimeException(e);
        }
    }

    private static BufferedImage convertByteArrayToJPEG(byte[] byteArray) {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        BufferedImage image;

        try {
            image = ImageIO.read(bis);
        } catch (IOException e) {
            // TODO: throw a custom exception
            throw new RuntimeException(e);
        }

        if (image == null) {
            throw new RuntimeException("Image is null");
        }
        return image;
    }

    public static byte[] readImageFromDisk(String path) {
        byte[] byteArray;
        try {
            BufferedImage image = ImageIO.read(new File(path));
            byteArray = convertJPEGToByteArray(image);
        } catch (IOException e) {
            // TODO: throw a custom exception
            throw new RuntimeException(e);
        }
        return byteArray;
    }

    private static byte[] convertJPEGToByteArray(BufferedImage image) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpeg", bos);
        } catch (IOException e) {
            // TODO: throw a custom exception
            throw new RuntimeException(e);
        }
        return bos.toByteArray();
    }

}
