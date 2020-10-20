package com.e2esoft.vcam.ds;

import com4j.*;

/**
 * IVCamRenderer interface
 */
@IID("{CCFBFF50-ED42-4CE4-8E89-7324FB082711}")
public interface IVCamRenderer extends Com4jObject {
  // Methods:
  /**
   * @param rgb24Buffer Mandatory java.lang.Object parameter.
   * @param width Mandatory int parameter.
   * @param height Mandatory int parameter.
   */

  @DISPID(1610678272) //= 0x60010000. The runtime will prefer the VTID if present
  @VTID(3)
  void playBuffer24(
    @MarshalAs(NativeType.VARIANT) Object rgb24Buffer,
    int width,
    int height);


  /**
   * @param rgb32Buffer Mandatory java.lang.Object parameter.
   * @param width Mandatory int parameter.
   * @param height Mandatory int parameter.
   */

  @DISPID(1610678273) //= 0x60010001. The runtime will prefer the VTID if present
  @VTID(4)
  void playBuffer32(
    @MarshalAs(NativeType.VARIANT) Object rgb32Buffer,
    int width,
    int height);


  /**
   * @param pRgb32Buffer Mandatory java.lang.Object parameter.
   * @param width Mandatory int parameter.
   * @param height Mandatory int parameter.
   * @param px Mandatory int parameter.
   * @param py Mandatory int parameter.
   * @param whichOne Mandatory int parameter.
   */

  @DISPID(1610678274) //= 0x60010002. The runtime will prefer the VTID if present
  @VTID(5)
  void addOverlay(
    @MarshalAs(NativeType.VARIANT) Object pRgb32Buffer,
    int width,
    int height,
    int px,
    int py,
    int whichOne);


  /**
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

  @DISPID(1610678275) //= 0x60010003. The runtime will prefer the VTID if present
  @VTID(6)
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


  /**
   * @param func Mandatory int parameter.
   */

  @DISPID(1610678276) //= 0x60010004. The runtime will prefer the VTID if present
  @VTID(7)
  void moreProcess(
    int func);


  // Properties:
}
