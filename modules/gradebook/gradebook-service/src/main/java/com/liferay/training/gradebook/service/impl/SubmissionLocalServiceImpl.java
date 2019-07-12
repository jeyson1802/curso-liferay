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

package com.liferay.training.gradebook.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.model.Submission;
import com.liferay.training.gradebook.service.base.SubmissionLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the submission local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.training.gradebook.service.SubmissionLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SubmissionLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.training.gradebook.model.Submission",
	service = AopService.class
)
public class SubmissionLocalServiceImpl extends SubmissionLocalServiceBaseImpl {
	
	public Submission addSubmission(long assignmentId, long studentId, String submissionText,
            ServiceContext serviceContext) throws PortalException {

        //
        // ( 1 ) - Get Assignment.
        //

        Assignment assignment = assignmentPersistence.findByPrimaryKey(assignmentId);

        //
        // ( 2 ) - Get user.
        //
        // Even though we will be not using the user object in this method, fetching it validates 
        // its existence.
        //

        long userId = serviceContext.getUserId();

//        User user = userLocalService.getUser(userId);

        //
        // ( 3 ) - Get student user (studentId).
        //
         // Even though we will be not using the user object in this method, fetching it validates 
        // its existence.
        //

//        User studentUser = userLocalService.getUser(studentId);

        //
        // ( 4 ) - Generate submission id
        //

        long submissionId = counterLocalService.increment(Submission.class.getName());

        //
        // ( 5 ) - Create Submission
        //

        Submission submission = submissionLocalService.createSubmission(submissionId);

        // Populate submission fields

        submission.setSubmissionId(submissionId);
        submission.setAssignmentId(assignmentId);
        submission.setCompanyId(assignment.getCompanyId());
        submission.setGroupId(assignment.getGroupId());
        submission.setCreateDate(new Date());
        submission.setModifiedDate(new Date());

        submission.setUserId(userId);
        submission.setGrade(-1);
        submission.setStudentId(studentId);
        submission.setSubmissionText(submissionText);
        submission.setSubmitDate(new Date());

        //
        // ( 6 ) - Persist the entity.
        //

        submission = super.addSubmission(submission);

        //
        // ( 7 ) - Return the updated Submission.
        //

        return submission;
    }
	
	@Override
	public Submission addSubmission(Submission submission) {
    	throw new UnsupportedOperationException("Not supported.");
	}
	
    public List<Submission> getSubmissionsByAssignment(long groupId, long assignmentId) {

        //
        // ( 8 ) - Get submissions by groupId.
        //

        return submissionPersistence.findByG_A(groupId, assignmentId);
    }

    public List<Submission> getSubmissionsByAssignment(long groupId, long assignmentId, int start, int end) {

        //
        // ( 9 ) - Get submissions by groupId and assignmentId.
        //

        return submissionPersistence.findByG_A(groupId, assignmentId, start, end);
    }

    public int getSubmissionsCountByAssignment(long groupId, long assignmentId) {

        //
        // ( 10 ) - Get count by groupId and assignmentId.
        //

        return submissionPersistence.countByG_A(groupId, assignmentId);
    }

    public Submission gradeAndCommentSubmission(long submissionId, int grade, String comment) throws PortalException {

        //
        // ( 11 ) - Get Submission.
        //

        Submission submission = this.getSubmission(submissionId);    

        //
        // ( 12 ) - Update following fields: grade, comment, modifiedDate.
        //
        submission.setGrade(grade);
        submission.setComment(comment);
        submission.setModifiedDate(new Date());

        //
        // ( 13 ) - Return updated submission.
        //

        return super.updateSubmission(submission);
    }

    public Submission gradeSubmission(long submissionId, int grade) throws PortalException {

        //
        // ( 14 ) - Get Submission.
        //

        Submission submission = this.getSubmission(submissionId);

        //
        // ( 15 ) - Update following fields: grade, modifiedDate.
        //

        submission.setGrade(grade);
        submission.setModifiedDate(new Date());


        //
        // ( 16 ) - Return updated submission.
        //

        return super.updateSubmission(submission);
    }

    public Submission updateSubmission(long submissionId, String submissionText, ServiceContext serviceContext)
            throws PortalException {

        //
        // ( 17 ) - Get Submission.
        //

        Submission submission = this.getSubmission(submissionId);

        //    
        // ( 18 ) - Update following fields: submissionText, submitDate, modifiedDate.
        //

        submission.setSubmissionText(submissionText);
        submission.setSubmitDate(new Date());
        submission.setModifiedDate(new Date());

        //
        // ( 19 ) - Persist the entity.
        //

        submission = super.updateSubmission(submission);

        //
        // ( 20 ) - Return updated submission.
        //

        return submission;
    }
    
    @Override
	public Submission updateSubmission(Submission submission) {
		throw new UnsupportedOperationException("Not supported.");
	}
}