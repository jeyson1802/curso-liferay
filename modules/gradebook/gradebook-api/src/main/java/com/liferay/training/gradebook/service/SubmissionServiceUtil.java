/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.training.gradebook.service;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Submission. This utility wraps
 * <code>com.liferay.training.gradebook.service.impl.SubmissionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SubmissionService
 * @generated
 */
@ProviderType
public class SubmissionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.training.gradebook.service.impl.SubmissionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.training.gradebook.model.Submission addSubmission(
			long assignmentId, long studentId, String submissionText,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addSubmission(
			assignmentId, studentId, submissionText, serviceContext);
	}

	public static com.liferay.training.gradebook.model.Submission
			deleteSubmission(long submissionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteSubmission(submissionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List
		<com.liferay.training.gradebook.model.Submission>
			getSubmissionsByAssignment(long groupId, long assignmentId) {

		return getService().getSubmissionsByAssignment(groupId, assignmentId);
	}

	public static java.util.List
		<com.liferay.training.gradebook.model.Submission>
			getSubmissionsByAssignment(
				long groupId, long assignmentId, int start, int end) {

		return getService().getSubmissionsByAssignment(
			groupId, assignmentId, start, end);
	}

	public static int getSubmissionsCountByAssignment(
		long groupId, long assignmentId) {

		return getService().getSubmissionsCountByAssignment(
			groupId, assignmentId);
	}

	public static com.liferay.training.gradebook.model.Submission
			gradeAndCommentSubmission(
				long submissionId, int grade, String comment)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().gradeAndCommentSubmission(
			submissionId, grade, comment);
	}

	public static com.liferay.training.gradebook.model.Submission
			gradeSubmission(long submissionId, int grade)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().gradeSubmission(submissionId, grade);
	}

	public static com.liferay.training.gradebook.model.Submission
			updateSubmission(
				long submissionId, String submissionText,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateSubmission(
			submissionId, submissionText, serviceContext);
	}

	public static SubmissionService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SubmissionService, SubmissionService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SubmissionService.class);

		ServiceTracker<SubmissionService, SubmissionService> serviceTracker =
			new ServiceTracker<SubmissionService, SubmissionService>(
				bundle.getBundleContext(), SubmissionService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}