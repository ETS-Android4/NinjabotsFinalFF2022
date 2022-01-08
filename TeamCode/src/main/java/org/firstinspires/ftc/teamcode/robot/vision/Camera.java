package org.firstinspires.ftc.teamcode.robot.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

public class Camera {
    public OpenCvWebcam webcam;
    public CameraPipeline pipeline;
    private int average1;
    private int average2;
    private CameraPipeline.DuckPosition pos;
    public Camera(HardwareMap hardwareMap){
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);
        pipeline = new CameraPipeline();
        webcam.setPipeline(pipeline);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(320, 240);
            }
            @Override
            public void onError(int errorCode) {

            }
        });
    }

    public void detect(){
        average1 = pipeline.getRegion1();
        average2 = pipeline.getRegion2();
        pos = pipeline.getPosition();


    }
    public int getAverage1(){
        return average1;
    }
    public int getAverage2(){return average2;}
    public CameraPipeline.DuckPosition getPosition(){return pos;}
}
