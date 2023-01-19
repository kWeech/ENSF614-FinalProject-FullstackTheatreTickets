package group14.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group14.Backend.model.Voucher;
import group14.Backend.service.VoucherService;

@RestController
@RequestMapping("/voucher")
@CrossOrigin
public class VoucherController {
    private final VoucherService voucherService;

    /**
     * @param voucherService
     */
    @Autowired
    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    /**
     * returns voucher via GET /voucher/{voucherId}
     * @param voucherId
     * @return
     */
    @GetMapping("/{voucherId}")
    public Voucher getVoucher(@PathVariable("voucherId") Long voucherId){
        return voucherService.getVoucher(voucherId);
    }
}
