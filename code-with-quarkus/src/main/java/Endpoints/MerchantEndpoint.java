package Endpoints;

import controller.PaymentController;
import controller.ReportController;
import controller.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.acme.*;
import org.jboss.logging.Logger;

import javax.ws.rs.Produces;
import java.util.List;

@Path("/merchants")
public class MerchantEndpoint {
    UserService userService = new UserService();
    PaymentController paymentController = new PaymentController();
    ReportController reportController = new ReportController();

    private static final Logger LOG = Logger.getLogger(MerchantEndpoint.class);


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MerchantDTO getMerchant(@PathParam("id") String id) {
        return convertMerchantDTO(userService.getMerchant(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createMerchant(MerchantDTO merchantDTO)
    {
        Merchant merchant = convertMerchantDTO(merchantDTO);
        return userService.createMerchant(merchant);
    }

    @POST
    @Path ("/payments")
    @Consumes(MediaType.APPLICATION_JSON)
    public String makePayment(PaymentDTO paymentDTO) throws Exception {
        LOG.info("Received PaymentDTO: " + paymentDTO.getToken() + " " + paymentDTO.getMid());
        paymentController.makePayment(paymentDTO);
        return "Payment made";
    }

    @GET
    @Path("payments/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MerchantPaymentDTO> getPayments(@PathParam("id") String id) {
        return reportController.getMerchantReport(id);
    }


    private Merchant convertMerchantDTO(MerchantDTO merchantDTO){
        Merchant merchant = new Merchant();
        merchant.setFirstName(merchantDTO.getFirstName());
        merchant.setLastName(merchantDTO.getLastName());
        merchant.setId(merchantDTO.getId());
        merchant.setCpr(merchantDTO.getCpr());
        merchant.setAccountId(merchantDTO.getAccount());

        return merchant;
    }

    private MerchantDTO convertMerchantDTO(Merchant merchant) {
        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setFirstName(merchant.getFirstName());
        merchantDTO.setLastName(merchant.getLastName());
        merchantDTO.setCpr(merchant.getCpr());
        merchantDTO.setId(merchant.getId());
        merchantDTO.setAccount(merchant.getAccountId());


        return merchantDTO;
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getService(){
        return "Hello from REST";
    }

    @DELETE
    @Path("{id}")
    public void deleteMerchant(@PathParam("id") String id) {
        userService.deleteUser(id);
    }









}
