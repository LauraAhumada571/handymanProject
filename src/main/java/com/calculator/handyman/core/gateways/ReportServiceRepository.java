package com.calculator.handyman.core.gateways;

import com.calculator.handyman.core.domain.reportService.ReportService;
import com.calculator.handyman.infraestructure.gateways.repositories.models.QueryInputDBO;
import com.calculator.handyman.shared.domain.ReportServiceWeek;

import java.util.List;

public interface ReportServiceRepository {

    void store (ReportService reportService);

    List<ReportServiceWeek> getListReport(QueryInputDBO queryInputDBO);
}

