package Endpoints;

import controller.UserController;
import jakarta.batch.api.BatchProperty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.acme.*;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/merchants")
public class MerchantEndpoint {
    UserController userController = new UserController();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MerchantDTO getMerchant(@PathParam("id") String id) {
        return convertMerchantDTO(userController.getMerchant(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createMerchant(MerchantDTO merchantDTO)
    {
        Merchant merchant = convertMerchantDTO(merchantDTO);
        return userController.createMerchant(merchant);
    }

    @Path ("/payments")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void makePayment(PaymentDTO paymentDTO){



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









}
