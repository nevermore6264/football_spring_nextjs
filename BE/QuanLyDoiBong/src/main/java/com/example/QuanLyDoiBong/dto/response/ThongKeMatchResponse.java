package com.example.QuanLyDoiBong.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThongKeMatchResponse {
    private String TeamName;
    private long soTranDaDau;
    private long soBanThua;
    private long soBanThang;
    private long tongSoTranThang;
    private long tongSoTranThua;
    private long tongSoTranHoa;
    private long point;
}
