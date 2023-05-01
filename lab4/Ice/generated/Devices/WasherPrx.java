//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.9
//
// <auto-generated>
//
// Generated from file `smarthome.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Devices;

public interface WasherPrx extends com.zeroc.Ice.ObjectPrx
{
    default int runWashing(int programId)
    {
        return runWashing(programId, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default int runWashing(int programId, java.util.Map<String, String> context)
    {
        return _iceI_runWashingAsync(programId, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Integer> runWashingAsync(int programId)
    {
        return _iceI_runWashingAsync(programId, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Integer> runWashingAsync(int programId, java.util.Map<String, String> context)
    {
        return _iceI_runWashingAsync(programId, context, false);
    }

    /**
     * @hidden
     * @param iceP_programId -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Integer> _iceI_runWashingAsync(int iceP_programId, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Integer> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "runWashing", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeInt(iceP_programId);
                 }, istr -> {
                     int ret;
                     ret = istr.readInt();
                     return ret;
                 });
        return f;
    }

    default int planWashing(int programId, Time time)
    {
        return planWashing(programId, time, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default int planWashing(int programId, Time time, java.util.Map<String, String> context)
    {
        return _iceI_planWashingAsync(programId, time, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Integer> planWashingAsync(int programId, Time time)
    {
        return _iceI_planWashingAsync(programId, time, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Integer> planWashingAsync(int programId, Time time, java.util.Map<String, String> context)
    {
        return _iceI_planWashingAsync(programId, time, context, false);
    }

    /**
     * @hidden
     * @param iceP_programId -
     * @param iceP_time -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Integer> _iceI_planWashingAsync(int iceP_programId, Time iceP_time, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Integer> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "planWashing", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeInt(iceP_programId);
                     Time.ice_write(ostr, iceP_time);
                 }, istr -> {
                     int ret;
                     ret = istr.readInt();
                     return ret;
                 });
        return f;
    }

    default int cancelPlanedWashing()
    {
        return cancelPlanedWashing(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default int cancelPlanedWashing(java.util.Map<String, String> context)
    {
        return _iceI_cancelPlanedWashingAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Integer> cancelPlanedWashingAsync()
    {
        return _iceI_cancelPlanedWashingAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Integer> cancelPlanedWashingAsync(java.util.Map<String, String> context)
    {
        return _iceI_cancelPlanedWashingAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Integer> _iceI_cancelPlanedWashingAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Integer> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "cancelPlanedWashing", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     int ret;
                     ret = istr.readInt();
                     return ret;
                 });
        return f;
    }

    default PlannedProgram getPlannedProgram()
    {
        return getPlannedProgram(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default PlannedProgram getPlannedProgram(java.util.Map<String, String> context)
    {
        return _iceI_getPlannedProgramAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<PlannedProgram> getPlannedProgramAsync()
    {
        return _iceI_getPlannedProgramAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<PlannedProgram> getPlannedProgramAsync(java.util.Map<String, String> context)
    {
        return _iceI_getPlannedProgramAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<PlannedProgram> _iceI_getPlannedProgramAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<PlannedProgram> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getPlannedProgram", com.zeroc.Ice.OperationMode.Idempotent, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     PlannedProgram ret;
                     ret = PlannedProgram.ice_read(istr);
                     return ret;
                 });
        return f;
    }

    default Program programDetails(int programId)
    {
        return programDetails(programId, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default Program programDetails(int programId, java.util.Map<String, String> context)
    {
        return _iceI_programDetailsAsync(programId, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Program> programDetailsAsync(int programId)
    {
        return _iceI_programDetailsAsync(programId, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Program> programDetailsAsync(int programId, java.util.Map<String, String> context)
    {
        return _iceI_programDetailsAsync(programId, context, false);
    }

    /**
     * @hidden
     * @param iceP_programId -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Program> _iceI_programDetailsAsync(int iceP_programId, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Program> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "programDetails", com.zeroc.Ice.OperationMode.Idempotent, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeInt(iceP_programId);
                 }, istr -> {
                     Program ret;
                     ret = Program.ice_read(istr);
                     return ret;
                 });
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static WasherPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), WasherPrx.class, _WasherPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static WasherPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), WasherPrx.class, _WasherPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static WasherPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), WasherPrx.class, _WasherPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static WasherPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), WasherPrx.class, _WasherPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static WasherPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, WasherPrx.class, _WasherPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static WasherPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, WasherPrx.class, _WasherPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default WasherPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (WasherPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default WasherPrx ice_adapterId(String newAdapterId)
    {
        return (WasherPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default WasherPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (WasherPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default WasherPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (WasherPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default WasherPrx ice_invocationTimeout(int newTimeout)
    {
        return (WasherPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default WasherPrx ice_connectionCached(boolean newCache)
    {
        return (WasherPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default WasherPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (WasherPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default WasherPrx ice_secure(boolean b)
    {
        return (WasherPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default WasherPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (WasherPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default WasherPrx ice_preferSecure(boolean b)
    {
        return (WasherPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default WasherPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (WasherPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default WasherPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (WasherPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default WasherPrx ice_collocationOptimized(boolean b)
    {
        return (WasherPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default WasherPrx ice_twoway()
    {
        return (WasherPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default WasherPrx ice_oneway()
    {
        return (WasherPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default WasherPrx ice_batchOneway()
    {
        return (WasherPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default WasherPrx ice_datagram()
    {
        return (WasherPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default WasherPrx ice_batchDatagram()
    {
        return (WasherPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default WasherPrx ice_compress(boolean co)
    {
        return (WasherPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default WasherPrx ice_timeout(int t)
    {
        return (WasherPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default WasherPrx ice_connectionId(String connectionId)
    {
        return (WasherPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default WasherPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (WasherPrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::Devices::Washer";
    }
}
