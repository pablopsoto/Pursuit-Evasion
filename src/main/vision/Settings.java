package main.vision;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;



public class Settings
{


    private IntegerProperty lineCount = new SimpleIntegerProperty(0);
    private DoubleProperty scanLineLength = new SimpleDoubleProperty(50);
    private BooleanProperty limitToScanLineLength = new SimpleBooleanProperty(true);
    private IntegerProperty scanLineCount = new SimpleIntegerProperty(90);


    private static Settings settings = new Settings();

    private Settings()
    {
    }

    public static Settings get()
    {
        return settings;
    }


    public final IntegerProperty lineCountProperty()
    {
        return this.lineCount;
    }

    public final int getLineCount()
    {
        return this.lineCount.get();
    }


    public double getScanLineLength()
    {
        return scanLineLength.get();
    }

    public boolean isLimitToScanLineLength()
    {
        return limitToScanLineLength.get();
    }

    public int getScanLineCount()
    {
        return scanLineCount.get();
    }


    public void setScanLineLength(int scanLineLength)
    {
        this.scanLineLength = new SimpleDoubleProperty(scanLineLength);
    }
}
