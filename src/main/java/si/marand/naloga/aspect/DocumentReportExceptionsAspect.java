package si.marand.naloga.aspect;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import si.marand.naloga.entities.DocumentReportEntity;
import si.marand.naloga.repositories.DocumentReportRepository;

@Aspect
@Component
public class DocumentReportExceptionsAspect {
    private final DocumentReportRepository documentReportRepository;
    private final HttpServletRequest request;

    @Autowired
    public DocumentReportExceptionsAspect(DocumentReportRepository documentReportRepository, HttpServletRequest request) {
        this.documentReportRepository = documentReportRepository;
        this.request = request;
    }

    /**
     * Writes a document report in case of an exception.
     *
     * @param joinPoint join point
     * @param ex throwable
     */
    @AfterThrowing(
            pointcut = "execution(* si.marand.naloga.cotroller.DoctorControllerImpl.createDoctor(..))",
            throwing = "ex")
    public void saveDocumentReport(JoinPoint joinPoint, Throwable ex) {

        DocumentReportEntity documentReport = new DocumentReportEntity();
        documentReport.setErrorMessage(ex.getMessage());
        LocalDateTime executionStartTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(request.getSession().getLastAccessedTime()), ZoneId.systemDefault());
        documentReport.setExecutionStartTime(executionStartTime);

        documentReportRepository.save(documentReport);
    }
}
