
package client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "InsulinService", targetNamespace = "http://server/", wsdlLocation = "http://localhost:8081/insulin?WSDL")
public class InsulinService
    extends Service
{

    private final static URL INSULINSERVICE_WSDL_LOCATION;
    private final static WebServiceException INSULINSERVICE_EXCEPTION;
    private final static QName INSULINSERVICE_QNAME = new QName("http://server/", "InsulinService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8081/insulin?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        INSULINSERVICE_WSDL_LOCATION = url;
        INSULINSERVICE_EXCEPTION = e;
    }

    public InsulinService() {
        super(__getWsdlLocation(), INSULINSERVICE_QNAME);
    }

    public InsulinService(WebServiceFeature... features) {
        super(__getWsdlLocation(), INSULINSERVICE_QNAME, features);
    }

    public InsulinService(URL wsdlLocation) {
        super(wsdlLocation, INSULINSERVICE_QNAME);
    }

    public InsulinService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, INSULINSERVICE_QNAME, features);
    }

    public InsulinService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public InsulinService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Insulin
     */
    @WebEndpoint(name = "InsulinPort")
    public Insulin getInsulinPort() {
        return super.getPort(new QName("http://server/", "InsulinPort"), Insulin.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Insulin
     */
    @WebEndpoint(name = "InsulinPort")
    public Insulin getInsulinPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server/", "InsulinPort"), Insulin.class, features);
    }

    private static URL __getWsdlLocation() {
        if (INSULINSERVICE_EXCEPTION!= null) {
            throw INSULINSERVICE_EXCEPTION;
        }
        return INSULINSERVICE_WSDL_LOCATION;
    }

}
