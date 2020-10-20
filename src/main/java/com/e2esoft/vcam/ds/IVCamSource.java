package com.e2esoft.vcam.ds;

import com4j.*;

/**
 * IVCamSource interface
 */
@IID("{F8AF4078-A31A-4651-9835-47E54E41520E}")
public interface IVCamSource extends Com4jObject {
  // Methods:
  /**
   * @param pRegStr Mandatory java.lang.String parameter.
   */

  @DISPID(1610678272) //= 0x60010000. The runtime will prefer the VTID if present
  @VTID(3)
  void setLicenseCode(
    String pRegStr);


  /**
   * @param lIndex Mandatory int parameter.
   * @return  Returns a value of type int
   */

  @DISPID(1610678273) //= 0x60010001. The runtime will prefer the VTID if present
  @VTID(4)
  int getProp(
    int lIndex);


  /**
   * @param lIndex Mandatory int parameter.
   * @param lValue Mandatory int parameter.
   */

  @DISPID(1610678274) //= 0x60010002. The runtime will prefer the VTID if present
  @VTID(5)
  void setProp(
    int lIndex,
    int lValue);


  /**
   */

  @DISPID(1610678275) //= 0x60010003. The runtime will prefer the VTID if present
  @VTID(6)
  void captureScreen();


  /**
   * @param x Mandatory int parameter.
   * @param y Mandatory int parameter.
   * @param width Mandatory int parameter.
   * @param height Mandatory int parameter.
   */

  @DISPID(1610678276) //= 0x60010004. The runtime will prefer the VTID if present
  @VTID(7)
  void captureScreenFixed(
    int x,
    int y,
    int width,
    int height);


  /**
   */

  @DISPID(1610678277) //= 0x60010005. The runtime will prefer the VTID if present
  @VTID(8)
  void stopCaptureScreen();


  /**
   */

  @DISPID(1610678278) //= 0x60010006. The runtime will prefer the VTID if present
  @VTID(9)
  @ReturnValue(type=NativeType.HRESULT)
  int isOpened();


  /**
   * <p>
   * property UsingApplication
   * </p>
   * <p>
   * Getter method for the COM property "UsingApplication"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(10)
  String usingApplication();


  // Properties:
}
