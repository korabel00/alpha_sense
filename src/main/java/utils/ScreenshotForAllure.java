package utils;

import com.codeborne.selenide.Screenshots;
import io.qameta.allure.Attachment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class ScreenshotForAllure {

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] attachment() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.readAllBytes(Objects.requireNonNull(screenshot).toPath());
    }
}
