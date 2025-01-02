package com.sjitzooi.templatelibrary_sql.entity.TemplateParts;

import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {
    private int limit;  // Number of items per page
    private int page; // Starting point of the current page
    private int total;  // Total number of items in the dataset
    private int totalPages;  // Total number of pages
    private String sortField;  // Field by which the list is sorted (e.g., "title", "date")
    private String sortOrder;  // Sort order ("asc" or "desc")
    private boolean hasNextPage;  // Flag indicating if there are more items after the current page
    private boolean hasPreviousPage;  // Flag indicating if there are items before the current page
}
