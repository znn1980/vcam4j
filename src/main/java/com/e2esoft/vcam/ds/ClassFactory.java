package com.e2esoft.vcam.ds;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
	private ClassFactory() {
	} // instanciation is not allowed

	/**
	 * VCamSDK Class
	 */
	public static IVCamSDK createVCamSDK() {
		return COM4J.createInstance(IVCamSDK.class, "{79A1C773-A400-4B07-B172-267113181C5B}");
	}

	/**
	 * VCamSource Class
	 */
	public static IVCamSource createVCamSource() {
		return COM4J.createInstance(IVCamSource.class, "{1A5C04B6-140D-4C05-9902-2E1700171F3E}");
	}

	/**
	 * VCamRenderer Class
	 */
	public static IVCamRenderer createVCamRenderer() {
		return COM4J.createInstance(IVCamRenderer.class, "{B6A665C1-955A-4D53-BF7B-A26D9D4DC27A}");
	}
}
