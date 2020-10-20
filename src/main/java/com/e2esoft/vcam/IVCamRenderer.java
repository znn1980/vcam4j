package com.e2esoft.vcam;

import com4j.*;

/**
 * IVCamRenderer Interface
 */
@IID("{855DAEFD-6664-49C4-BBAA-813A9A41659E}")
public interface IVCamRenderer extends Com4jObject {
  // Methods:
  /**
   * @param friendly_name Mandatory java.lang.String parameter.
   */

  @VTID(3)
  void setCurrentDevice(
    String friendly_name);


  /**
   * @param width Mandatory int parameter.
   * @param height Mandatory int parameter.
   * @param fps Mandatory int parameter.
   * @param format Mandatory int parameter.
   * @param one_size_only Mandatory int parameter.
   */

  @VTID(4)
  void setOutputFormat(
    int width,
    int height,
    int fps,
    int format,
    int one_size_only);


  /**
   * @param width Mandatory Holder<Integer> parameter.
   * @param height Mandatory Holder<Integer> parameter.
   * @param fps Mandatory Holder<Integer> parameter.
   * @param format Mandatory Holder<Integer> parameter.
   * @param one_size_only Mandatory Holder<Integer> parameter.
   */

  @VTID(5)
  void getOutputFormat(
    Holder<Integer> width,
    Holder<Integer> height,
    Holder<Integer> fps,
    Holder<Integer> format,
    Holder<Integer> one_size_only);


  /**
   * @param fill_mode Mandatory int parameter.
   */

  @VTID(6)
  void setFillMode(
    int fill_mode);


  /**
   * @return  Returns a value of type int
   */

  @VTID(7)
  int getFillMode();


  /**
   * @param mirror Mandatory int parameter.
   */

  @VTID(8)
  void setMirror(
    int mirror);


  /**
   * @param flip Mandatory int parameter.
   */

  @VTID(9)
  void setFlip(
    int flip);


  /**
   * @param rotate Mandatory int parameter.
   */

  @VTID(10)
  void setRotateRight(
    int rotate);


  /**
   * @param i_rgb24_pixels Mandatory Holder<Byte> parameter.
   * @param i_width Mandatory int parameter.
   * @param i_height Mandatory int parameter.
   * @param i_stride Mandatory int parameter.
   */

  @VTID(11)
  void sendFrame(
    Holder<Byte> i_rgb24_pixels,
    int i_width,
    int i_height,
    int i_stride);


  /**
   * @param i_rgb32_pixels Mandatory Holder<Byte> parameter.
   * @param i_width Mandatory int parameter.
   * @param i_height Mandatory int parameter.
   * @param i_stride Mandatory int parameter.
   */

  @VTID(12)
  void sendFrame32(
    Holder<Byte> i_rgb32_pixels,
    int i_width,
    int i_height,
    int i_stride);


  /**
   * @param i_yuy2 Mandatory Holder<Byte> parameter.
   * @param i_width Mandatory int parameter.
   * @param i_height Mandatory int parameter.
   * @param i_stride Mandatory int parameter.
   */

  @VTID(13)
  void sendFrameYUY2(
    Holder<Byte> i_yuy2,
    int i_width,
    int i_height,
    int i_stride);


  /**
   * @param i_nv12 Mandatory Holder<Byte> parameter.
   * @param i_width Mandatory int parameter.
   * @param i_height Mandatory int parameter.
   * @param i_stride Mandatory int parameter.
   */

  @VTID(14)
  void sendFrameNV12(
    Holder<Byte> i_nv12,
    int i_width,
    int i_height,
    int i_stride);


  /**
   * @param i_rgb24_array Mandatory java.lang.Object parameter.
   * @param i_width Mandatory int parameter.
   * @param i_height Mandatory int parameter.
   */

  @VTID(15)
  void sendFrameEx(
    @MarshalAs(NativeType.VARIANT) Object i_rgb24_array,
    int i_width,
    int i_height);


  /**
   * @param i_x Mandatory int parameter.
   * @param i_y Mandatory int parameter.
   * @param i_width Mandatory int parameter.
   * @param i_height Mandatory int parameter.
   */

  @VTID(16)
  void captureScreen(
    int i_x,
    int i_y,
    int i_width,
    int i_height);


  /**
   * @param i_handle Mandatory long parameter.
   */

  @VTID(17)
  void setConnectionNotificationEvent(
    long i_handle);


  /**
   * @return  Returns a value of type int
   */

  @VTID(18)
  int getConnectedCount();


  /**
   */

  @VTID(19)
  @ReturnValue(type=NativeType.HRESULT)
  int isOpened();


  /**
   * @param filename Mandatory java.lang.String parameter.
   */

  @VTID(20)
  void snapshot(
    String filename);


  /**
   * @param file_name Mandatory java.lang.String parameter.
   */

  @VTID(21)
  void setIdleFileName(
    String file_name);


  /**
   * @param friendly_name Mandatory java.lang.String parameter.
   */

  @VTID(22)
  void setFriendlyName(
    String friendly_name);


  /**
   * @param license_code Mandatory java.lang.String parameter.
   */

  @VTID(23)
  void setLicenseCode(
    String license_code);


  // Properties:
}
