package com.vikash.API_Access_Control.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForbiddenResponse {     //class modal for authentication response with properties

	private String Forbidden_Message;
}