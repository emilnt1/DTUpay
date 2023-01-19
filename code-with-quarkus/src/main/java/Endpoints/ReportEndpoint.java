package Endpoints;

import controller.PaymentController;
import controller.ReportController;
import controller.UserService;
import org.acme.Transaction;
import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/reports")
public class ReportEndpoint {

    UserService userService = new UserService();
    PaymentController paymentController = new PaymentController();

    ReportController reportController = new ReportController();
    private static final Logger LOG = Logger.getLogger(MerchantEndpoint.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transaction> getCustomerReport() {
        return reportController.getReport();
    }



}
