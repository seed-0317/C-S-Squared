insert INTO csssquared.ers_user_roles (ur_id, ur_role) VALUES
  (1, 'Employee'),
  (2, 'Manager');


INSERT INTO csssquared.ers_reimbursement_status (rs_id, rs_status) VALUES
  (1, 'New'),
  (2,'Approved'),
  (3, 'Rejected');

INSERT INTO csssquared.ers_reimbusrement_type (rt_id, rt_type) VALUES
  (1, 'Travel'),
  (2, 'Training'),
  (3, 'Meals'),
  (4, 'Office Supplies');

INSERT into csssquared.ers_users (u_id, u_username, u_firstname, u_lastname, u_email, ur_id) VALUES
  (1, 'bjones', 'Bob', 'Jones', 'bjones@cssquared.com', 1),
  (2, 'bjohnson', 'Biff', 'Johnson', 'bjohnson@cssquared.com', 2);