

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Gamepad;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "3736: TeleOp", group = "TeleOp")
public class TeleOp extends OpMode {


	final static double JEWEL_MIN_RANGE  = 0;
	final static double JEWEL_MAX_RANGE  = 1;


	//double jewelPosition;

	//double jewelDelta = 0.1;

	//DcMotor basket;
	 DcMotor strafe;
	 DcMotor rightSideFront;
	 DcMotor rightSideBack;
	 DcMotor leftSideFront
	 DcMotor leftSideBack
	 //Servo jewel;


	public TeleOp() {

	}

	@Override
	public void init() {

		strafe = hardwareMap.dcMotor.get("STR");
		rightSideFront = hardwareMap.dcMotor.get("MRF");
		rightSideBack = hardwareMap.dcMotor.get("MRB");
		leftSideFront = hardwareMap.dcMotor.get("MLF");
		leftSideBack = hardwareMap.dcMotor.get("MLB");
		
		rightSideFront.setDirection(DcMotor.Direction.REVERSE);
rightSideBack.setDirection(DcMotor.Direction.FORWARD);
leftSideFront.setDirection(DcMotor.Direction.FORWARD);
leftSideBack.setDirection(DcMotor.Direction.REVERSE);
strafe.setDirection(DcMotor.Direction.REVERSE);
		//jewel = hardwareMap.servo.get("jewel");

		//jewelPosition = 0.1;
	}

		public void loop()
	{
		float y1 = gamepad1.left_stick_y;
		float y2 = gamepad1.right_stick_y;
		float y3 = gamepad2.left_stick_y;
		float y4 = gamepad2.right_stick_y;
		boolean dleft = gamepad1.dpad_left;
		boolean dright = gamepad1.dpad_right;

		y1 = Range.clip(y1, -1, 1);
		y2 = Range.clip(y2, -1, 1);
		y3 = Range.clip(y3, -1, 1);
		y4 = Range.clip(y4, -1, 1);

		y1 = (float)scaleInput(y1);
		y2 = (float)scaleInput(y2);
		y3 = (float)scaleInput(y3);
 y4 = (float)scaleInput(y4);


        leftSideFront.setPower(y1);
        leftSideBack.setPower(y1);
        rightSideFront.setPower(y2);
        rightSideBack.setPower(y2);

		if (gamepad1.dpad_left)
		{
			strafe.setPower(-1);
		}
		else if (gamepad1.dpad_right)
		{
			strafe.setPower(1);
		}
		else strafe.setPower(0);



		//if (gamepad1.a)
		//{
		//	jewelPosition += jewelDelta;
		//}

		//if (gamepad1.y)
		//{
		//	jewelPosition -= jewelDelta;
		//}



        //jewelPosition = Range.clip(jewelPosition, JEWEL_MIN_RANGE, JEWEL_MAX_RANGE);

		//jewel.setPosition(jewelPosition);

        telemetry.addData("Text", "*** Robot Data***");
        //telemetry.addData("jewel", "jewel:  " + String.format("%.2f", jewelPosition));
	}


	@Override
	public void stop() {

	}

	double scaleInput(double dVal)  {
		double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
				0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

		int index = (int) (dVal * 16.0);

		if (index < 0) {
			index = -index;
		}

		if (index > 16) {
			index = 16;
		}

		double dScale = 0.0;
		if (dVal < 0) {
			dScale = -scaleArray[index];
		} else {
			dScale = scaleArray[index];
		}

		return dScale;
	}
}