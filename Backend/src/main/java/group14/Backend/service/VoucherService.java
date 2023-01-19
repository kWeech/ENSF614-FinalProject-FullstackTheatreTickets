package group14.Backend.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group14.Backend.model.Voucher;
import group14.Backend.repository.VoucherRepository;

@Service
public class VoucherService {
    private final VoucherRepository voucherRepository;

    @Autowired
    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    
    /** 
     * Queries the database for a voucher ghiven an id and returns the voucher object
     * @param voucherId
     * @return Voucher
     */
    public Voucher getVoucher(Long voucherId) {
        Optional<Voucher> voucherById = voucherRepository.getVoucherById(voucherId);
        if (!voucherById.isPresent()) {
            System.out.println(voucherById);
            throw new IllegalStateException("Voucher does not exist or already used!");
        }
        Voucher voucher = voucherById.get();
        if(voucher.getExpiryDate().isBefore(LocalDate.now())){
            throw new IllegalStateException("Voucher is expired!");
        }
        this.voucherRepository.delete(voucher);
        return voucher;
    }
}
