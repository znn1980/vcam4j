package com.e2esoft.vcam.ds;

import com4j.*;

/**
 * IVCamSDK Interface
 */
@IID("{90973115-408F-428E-B0D8-BF92B998E403}")
public interface IVCamSDK extends Com4jObject {
  // Methods:
  /**
   * <p>
   * SetLicenseCode
   * </p>
   * @param pRegStr Mandatory java.lang.String parameter.
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  void setLicenseCode(
    String pRegStr);


  /**
   * <p>
   * PlayVideoFile
   * </p>
   * @param fileName Mandatory java.lang.String parameter.
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  void playVideoFile(
    String fileName);


  /**
   * <p>
   * CurrentVideoPosition
   * </p>
   * <p>
   * Getter method for the COM property "CurrentVideoPosition"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  int currentVideoPosition();


  /**
   * <p>
   * CurrentVideoPosition
   * </p>
   * <p>
   * Setter method for the COM property "CurrentVideoPosition"
   * </p>
   * @param pVal Mandatory int parameter.
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(10)
  void currentVideoPosition(
    int pVal);


  /**
   * <p>
   * CurrentVideoDuration
   * </p>
   * <p>
   * Getter method for the COM property "CurrentVideoDuration"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(11)
  int currentVideoDuration();


  /**
   * <p>
   * PlayVideoDevice
   * </p>
   * @param idx Mandatory int parameter.
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(12)
  void playVideoDevice(
    int idx);


  /**
   * <p>
   * IsPlaying
   * </p>
   * <p>
   * Getter method for the COM property "IsPlaying"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(13)
  boolean isPlaying();


  /**
   * <p>
   * StopPlay
   * </p>
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(14)
  void stopPlay();


  /**
   * <p>
   * PlayBuffer
   * </p>
   * @param rgb24Buffer Mandatory Holder<Byte> parameter.
   * @param width Mandatory int parameter.
   * @param height Mandatory int parameter.
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(15)
  void playBuffer(
    Holder<Byte> rgb24Buffer,
    int width,
    int height);


  /**
   * <p>
   * PlayBufferEx - automation
   * </p>
   * @param rgb24Buffer Mandatory java.lang.Object parameter.
   * @param width Mandatory int parameter.
   * @param height Mandatory int parameter.
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(16)
  void playBufferEx(
    @MarshalAs(NativeType.VARIANT) Object rgb24Buffer,
    int width,
    int height);


  /**
   * <p>
   * CaptureScreenFixed
   * </p>
   * @param x Mandatory int parameter.
   * @param y Mandatory int parameter.
   * @param width Mandatory int parameter.
   * @param height Mandatory int parameter.
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(17)
  void captureScreenFixed(
    int x,
    int y,
    int width,
    int height);


  /**
   * <p>
   * CaptureScreen
   * </p>
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(18)
  void captureScreen();


  /**
   * <p>
   * StopCaptureScreen
   * </p>
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(19)
  void stopCaptureScreen();


  /**
   * <p>
   * ChangeDefaultFPS
   * </p>
   * @param fps Mandatory int parameter.
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(20)
  void changeDefaultFPS(
    int fps);


  /**
   * <p>
   * ChangeDefaultVideoSize
   * </p>
   * @param width Mandatory int parameter.
   * @param height Mandatory int parameter.
   */

  @DISPID(14) //= 0xe. The runtime will prefer the VTID if present
  @VTID(21)
  void changeDefaultVideoSize(
    int width,
    int height);


  /**
   * <p>
   * AddOverlay
   * </p>
   * @param pRgb32Buffer Mandatory java.lang.Object parameter.
   * @param width Mandatory int parameter.
   * @param height Mandatory int parameter.
   * @param px Mandatory int parameter.
   * @param py Mandatory int parameter.
   * @param whichOne Mandatory int parameter.
   */

  @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
  @VTID(22)
  void addOverlay(
    @MarshalAs(NativeType.VARIANT) Object pRgb32Buffer,
    int width,
    int height,
    int px,
    int py,
    int whichOne);


  /**
   * <p>
   * AddText
   * </p>
   * @param olText Mandatory java.lang.String parameter.
   * @param ftName Mandatory java.lang.String parameter.
   * @param ftSize Mandatory int parameter.
   * @param ftStyle Mandatory int parameter.
   * @param ftColor Mandatory int parameter.
   * @param bkColor Mandatory int parameter.
   * @param px Mandatory int parameter.
   * @param py Mandatory int parameter.
   * @param bkType Mandatory int parameter.
   * @param whichOne Mandatory int parameter.
   */

  @DISPID(16) //= 0x10. The runtime will prefer the VTID if present
  @VTID(23)
  void addText(
    String olText,
    String ftName,
    int ftSize,
    int ftStyle,
    int ftColor,
    int bkColor,
    int px,
    int py,
    int bkType,
    int whichOne);


  // Properties:
}
