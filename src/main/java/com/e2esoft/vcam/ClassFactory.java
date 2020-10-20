package com.e2esoft.vcam;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed


  /**
   * VCamRenderer Class
   */
  public static IVCamRenderer createVCamRenderer() {
    return COM4J.createInstance( IVCamRenderer.class, "{3D2F839E-1186-4FCE-B772-B61FAE1ACED7}" );
  }

  /**
   * VCamRenderer Property Page Class
   */
  public static ISpecifyPropertyPages createVCamRendererPropertyPage() {
    return COM4J.createInstance( ISpecifyPropertyPages.class, "{5FC82DBE-C7EE-4443-984F-75DE03FA7C3A}" );
  }
}
