/**
 * Autogenerated by Avro
 * <p>
 * DO NOT EDIT DIRECTLY
 */
package ai.platon.pulsar.persist;

import ai.platon.pulsar.persist.gora.generated.GProtocolStatus;
import ai.platon.pulsar.persist.metadata.ProtocolStatusCodes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>ProtocolStatus class.</p>
 *
 * TODO: keep consistent with ResourceStatus
 *
 * @author vincent
 * @version $Id: $Id
 */
public class ProtocolStatus implements ProtocolStatusCodes {
    /** Constant <code>ARG_HTTP_CODE="httpCode"</code> */
    public static final String ARG_HTTP_CODE = "httpCode";
    /** Constant <code>ARG_REDIRECT_TO_URL="redirectTo"</code> */
    public static final String ARG_REDIRECT_TO_URL = "redirectTo";
    /** Constant <code>ARG_URL="url"</code> */
    public static final String ARG_URL = "url";
    /** Constant <code>ARG_RETRY_SCOPE="rsp"</code> */
    public static final String ARG_RETRY_SCOPE = "rsp";
    /** Constant <code>ARG_RETRY_REASON="rrs"</code> */
    public static final String ARG_RETRY_REASON = "rrs";

    /**
     * Content was not retrieved yet.
     */
    private static final short NOTFETCHED = 0;
    /**
     * Content was retrieved without errors.
     */
    private static final short SUCCESS = 1;
    /**
     * Content was not retrieved. Any further errors may be indicated in args.
     */
    private static final short FAILED = 2;

    /** Constant <code>STATUS_SUCCESS</code> */
    public static final ProtocolStatus STATUS_SUCCESS = new ProtocolStatus(SUCCESS, SUCCESS_OK);
    /** Constant <code>STATUS_NOTMODIFIED</code> */
    public static final ProtocolStatus STATUS_NOTMODIFIED = new ProtocolStatus(SUCCESS, NOT_MODIFIED);
    /** Constant <code>STATUS_NOTFETCHED</code> */
    public static final ProtocolStatus STATUS_NOTFETCHED = new ProtocolStatus(NOTFETCHED);

    /** Constant <code>STATUS_PROTO_NOT_FOUND</code> */
    public static final ProtocolStatus STATUS_PROTO_NOT_FOUND = ProtocolStatus.failed(PROTO_NOT_FOUND);
    /** Constant <code>STATUS_ACCESS_DENIED</code> */
    public static final ProtocolStatus STATUS_ACCESS_DENIED = ProtocolStatus.failed(ACCESS_DENIED);
    /** Constant <code>STATUS_NOTFOUND</code> */
    public static final ProtocolStatus STATUS_NOTFOUND = ProtocolStatus.failed(NOT_FOUND);
    // if a task is canceled, we do not save anything, if a task is retry, all the metadata is saved
    /** Constant <code>STATUS_CANCELED</code> */
    public static final ProtocolStatus STATUS_CANCELED = ProtocolStatus.failed(CANCELED);
    /** Constant <code>STATUS_EXCEPTION</code> */
    public static final ProtocolStatus STATUS_EXCEPTION = ProtocolStatus.failed(EXCEPTION);

    private static final HashMap<Short, String> majorCodes = new HashMap<>();
    private static final HashMap<Integer, String> minorCodes = new HashMap<>();

    static {
        majorCodes.put(NOTFETCHED, "NotFetched");
        majorCodes.put(SUCCESS, "Success");
        majorCodes.put(FAILED, "Failed");

        minorCodes.put(SUCCESS_OK, "OK");
        minorCodes.put(CREATED, "Created");
        minorCodes.put(MOVED, "Moved");
        minorCodes.put(TEMP_MOVED, "TempMoved");
        minorCodes.put(NOT_MODIFIED, "NotModified");

        minorCodes.put(PROTO_NOT_FOUND, "ProtoNotFound");
        minorCodes.put(ACCESS_DENIED, "AccessDenied");
        minorCodes.put(NOT_FOUND, "NotFound");
        minorCodes.put(REQUEST_TIMEOUT, "RequestTimeout");
        minorCodes.put(GONE, "Gone");

        minorCodes.put(UNKNOWN_HOST, "UnknownHost");
        minorCodes.put(ROBOTS_DENIED, "RobotsDenied");
        minorCodes.put(EXCEPTION, "Exception");
        minorCodes.put(REDIR_EXCEEDED, "RedirExceeded");
        minorCodes.put(WOULD_BLOCK, "WouldBlock");
        minorCodes.put(BLOCKED, "Blocked");

        minorCodes.put(RETRY, "Retry");
        minorCodes.put(CANCELED, "Canceled");
        minorCodes.put(THREAD_TIMEOUT, "ThreadTimeout");
        minorCodes.put(WEB_DRIVER_TIMEOUT, "WebDriverTimeout");
        minorCodes.put(SCRIPT_TIMEOUT, "ScriptTimeout");
    }

    private GProtocolStatus protocolStatus;

    /**
     * <p>Constructor for ProtocolStatus.</p>
     *
     * @param majorCode a short.
     */
    public ProtocolStatus(short majorCode) {
        this.protocolStatus = GProtocolStatus.newBuilder().build();
        setMajorCode(majorCode);
        setMinorCode(-1);
    }

    /**
     * <p>Constructor for ProtocolStatus.</p>
     *
     * @param majorCode a short.
     * @param minorCode a int.
     */
    public ProtocolStatus(short majorCode, int minorCode) {
        this.protocolStatus = GProtocolStatus.newBuilder().build();
        setMajorCode(majorCode);
        setMinorCode(minorCode);
    }

    private ProtocolStatus(GProtocolStatus protocolStatus) {
        Objects.requireNonNull(protocolStatus);
        this.protocolStatus = protocolStatus;
    }

    /**
     * <p>box.</p>
     *
     * @param protocolStatus a {@link ai.platon.pulsar.persist.gora.generated.GProtocolStatus} object.
     * @return a {@link ai.platon.pulsar.persist.ProtocolStatus} object.
     */
    @Nonnull
    public static ProtocolStatus box(GProtocolStatus protocolStatus) {
        return new ProtocolStatus(protocolStatus);
    }

    /**
     * <p>getMajorName.</p>
     *
     * @param code a short.
     * @return a {@link java.lang.String} object.
     */
    public static String getMajorName(int code) {
        return majorCodes.getOrDefault((short)code, "unknown");
    }

    /**
     * <p>getMinorName.</p>
     *
     * @param code a int.
     * @return a {@link java.lang.String} object.
     */
    public static String getMinorName(int code) {
        return minorCodes.getOrDefault(code, "unknown");
    }

    /**
     * <p>retry.</p>
     *
     * @param scope a {@link ai.platon.pulsar.persist.RetryScope} object.
     * @return a {@link ai.platon.pulsar.persist.ProtocolStatus} object.
     */
    @Nonnull
    public static ProtocolStatus retry(RetryScope scope) {
        return failed(ProtocolStatusCodes.RETRY, ARG_RETRY_SCOPE, scope);
    }

    /**
     * <p>retry.</p>
     *
     * @param scope a {@link ai.platon.pulsar.persist.RetryScope} object.
     * @param reason a {@link java.lang.Object} object.
     * @return a {@link ai.platon.pulsar.persist.ProtocolStatus} object.
     */
    @Nonnull
    public static ProtocolStatus retry(RetryScope scope, Object reason) {
        String reasonString;
        if (reason instanceof Exception) {
            reasonString = ((Exception) reason).getClass().getName();
        } else {
            reasonString = reason.toString();
        }
        return failed(ProtocolStatusCodes.RETRY, ARG_RETRY_SCOPE, scope, ARG_RETRY_REASON, reasonString);
    }

    /**
     * <p>cancel.</p>
     *
     * @param args a {@link java.lang.Object} object.
     * @return a {@link ai.platon.pulsar.persist.ProtocolStatus} object.
     */
    @Nonnull
    public static ProtocolStatus cancel(Object... args) {
        return failed(ProtocolStatusCodes.CANCELED, args);
    }

    /**
     * <p>failed.</p>
     *
     * @param minorCode a int.
     * @return a {@link ai.platon.pulsar.persist.ProtocolStatus} object.
     */
    @Nonnull
    public static ProtocolStatus failed(int minorCode) {
        return new ProtocolStatus(FAILED, minorCode);
    }

    /**
     * <p>failed.</p>
     *
     * @param minorCode a int.
     * @param args a {@link java.lang.Object} object.
     * @return a {@link ai.platon.pulsar.persist.ProtocolStatus} object.
     */
    @Nonnull
    public static ProtocolStatus failed(int minorCode, Object... args) {
        ProtocolStatus protocolStatus = new ProtocolStatus(FAILED, minorCode);

        if (args.length % 2 == 0) {
            Map<CharSequence, CharSequence> protocolStatusArgs = protocolStatus.getArgs();
            for (int i = 0; i < args.length - 1; i += 2) {
                if (args[i] != null && args[i + 1] != null) {
                    protocolStatusArgs.put(args[i].toString(), args[i + 1].toString());
                }
            }
        }

        return protocolStatus;
    }

    /**
     * <p>failed.</p>
     *
     * @param e a {@link java.lang.Throwable} object.
     * @return a {@link ai.platon.pulsar.persist.ProtocolStatus} object.
     */
    @Nonnull
    public static ProtocolStatus failed(Throwable e) {
        return failed(EXCEPTION, "error", e.getMessage());
    }

    /**
     * <p>fromMinor.</p>
     *
     * @param minorCode a int.
     * @return a {@link ai.platon.pulsar.persist.ProtocolStatus} object.
     */
    public static ProtocolStatus fromMinor(int minorCode) {
        if (minorCode == SUCCESS_OK || minorCode == NOT_MODIFIED) {
            return STATUS_SUCCESS;
        } else {
            return failed(minorCode);
        }
    }

    /**
     * <p>isTimeout.</p>
     *
     * @param protocalStatus a {@link ai.platon.pulsar.persist.ProtocolStatus} object.
     * @return a boolean.
     */
    public static boolean isTimeout(ProtocolStatus protocalStatus) {
        int code = protocalStatus.getMinorCode();
        return isTimeout(code);
    }

    /**
     * <p>isTimeout.</p>
     *
     * @param code a int.
     * @return a boolean.
     */
    public static boolean isTimeout(int code) {
        return code == REQUEST_TIMEOUT || code == THREAD_TIMEOUT || code == WEB_DRIVER_TIMEOUT || code == SCRIPT_TIMEOUT;
    }

    /**
     * <p>unbox.</p>
     *
     * @return a {@link ai.platon.pulsar.persist.gora.generated.GProtocolStatus} object.
     */
    public GProtocolStatus unbox() {
        return protocolStatus;
    }

    /**
     * <p>isNotFetched.</p>
     *
     * @return a boolean.
     */
    public boolean isNotFetched() {
        return getMajorCode() == NOTFETCHED;
    }

    /**
     * <p>isSuccess.</p>
     *
     * @return a boolean.
     */
    public boolean isSuccess() {
        return getMajorCode() == SUCCESS;
    }

    /**
     * <p>isFailed.</p>
     *
     * @return a boolean.
     */
    public boolean isFailed() {
        return getMajorCode() == FAILED;
    }

    /**
     * <p>isCanceled.</p>
     *
     * @return a boolean.
     */
    public boolean isCanceled() {
        return getMinorCode() == CANCELED;
    }

    /**
     * <p>isRetry.</p>
     *
     * @return a boolean.
     */
    public boolean isRetry() {
        return getMinorCode() == RETRY;
    }

    /**
     * <p>isRetry.</p>
     *
     * @param scope a {@link ai.platon.pulsar.persist.RetryScope} object.
     * @return a boolean.
     */
    public boolean isRetry(RetryScope scope) {
        RetryScope defaultScope = RetryScope.CRAWL;
        return getMinorCode() == RETRY && getArgOrDefault(ARG_RETRY_SCOPE, defaultScope.toString()).equals(scope.toString());
    }

    /**
     * <p>isRetry.</p>
     *
     * @param scope a {@link ai.platon.pulsar.persist.RetryScope} object.
     * @param reason a {@link java.lang.Object} object.
     * @return a boolean.
     */
    public boolean isRetry(RetryScope scope, Object reason) {
        String reasonString = "";
        if (reason instanceof Exception) {
            reasonString = ((Exception) reason).getClass().getName();
        } else {
            reasonString = reason.toString();
        }
        return isRetry(scope) && getArgOrDefault(ARG_RETRY_REASON, "").equals(reasonString);
    }

    /**
     * <p>isTempMoved.</p>
     *
     * @return a boolean.
     */
    public boolean isTempMoved() {
        return getMinorCode() == TEMP_MOVED;
    }

    /**
     * <p>isMoved.</p>
     *
     * @return a boolean.
     */
    public boolean isMoved() {
        return getMinorCode() == TEMP_MOVED || getMinorCode() == MOVED;
    }

    /**
     * <p>isTimeout.</p>
     *
     * @return a boolean.
     */
    public boolean isTimeout() {
        return isTimeout(this);
    }

    /**
     * <p>getMajorName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMajorName() {
        return getMajorName(getMajorCode());
    }

    /**
     * <p>getMajorCode.</p>
     *
     * @return a short.
     */
    public short getMajorCode() {
        return protocolStatus.getMajorCode().shortValue();
    }

    /**
     * <p>setMajorCode.</p>
     *
     * @param majorCode a short.
     */
    public void setMajorCode(short majorCode) {
        protocolStatus.setMajorCode((int) majorCode);
    }

    /**
     * <p>getMinorName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMinorName() {
        return getMinorName(getMinorCode());
    }

    /**
     * The detailed status code of the protocol, it must be compatible with standard http response code
     *
     * @return a int.
     */
    public int getMinorCode() {
        return protocolStatus.getMinorCode();
    }

    /**
     * <p>setMinorCode.</p>
     *
     * @param minorCode a int.
     */
    public void setMinorCode(int minorCode) {
        protocolStatus.setMinorCode(minorCode);
    }

    /**
     * <p>setMinorCode.</p>
     *
     * @param minorCode a int.
     * @param message a {@link java.lang.String} object.
     */
    public void setMinorCode(int minorCode, String message) {
        setMinorCode(minorCode);
        getArgs().put(getMinorName(), message);
    }

    /**
     * <p>getArgOrDefault.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param defaultValue a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public String getArgOrDefault(@NotNull String name, @NotNull String defaultValue) {
        return getArgs().getOrDefault(name, defaultValue).toString();
    }

    /**
     * <p>getArgs.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    public Map<CharSequence, CharSequence> getArgs() {
        return protocolStatus.getArgs();
    }

    /**
     * <p>setArgs.</p>
     *
     * @param args a {@link java.util.Map} object.
     */
    public void setArgs(Map<CharSequence, CharSequence> args) {
        protocolStatus.setArgs(args);
    }

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName() {
        return majorCodes.getOrDefault(getMajorCode(), "unknown") + "/"
                + minorCodes.getOrDefault(getMinorCode(), "unknown");
    }

    /**
     * <p>upgradeRetry.</p>
     *
     * @param scope a {@link ai.platon.pulsar.persist.RetryScope} object.
     */
    public void upgradeRetry(RetryScope scope) {
        getArgs().put(ARG_RETRY_SCOPE, scope.toString());
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        String str = getName() + " (" + getMajorCode() + "/" + getMinorCode() + ")";
        if (!getArgs().isEmpty()) {
            String args = getArgs().entrySet().stream()
                    .map(e -> e.getKey().toString() + ": " + e.getValue().toString())
                    .collect(Collectors.joining(", "));
            str += ", args=[" + args + "]";
        }
        return str;
   }
}
